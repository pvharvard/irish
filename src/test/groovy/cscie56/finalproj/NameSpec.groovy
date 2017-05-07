package cscie56.finalproj

import cscie56.finalproj.Name
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Name)
@Mock(Tune)
class NameSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Create a simple name"() {
        def name = new Name(name:'peter',index:1)
        expect:
        name.validate()
    }

    void "Create name without index"() {
        def name = new Name(name:'peter')
        expect:
        ! name.validate()
    }

    void "Check name can belong to multiple tunes"() {
        def name1 = new Name(name:'peter', index:1)
        name1.save(flush:true)
        def tune1 = new Tune(dance: Tune.Dance.JIG, primaryName: "The Banshee", tuneId: 8, numRecordings: 100, numTunebooks: 2711, names: [name1])
        tune1.save(flush:true)
        def tune2 = new Tune(dance: Tune.Dance.JIG, primaryName: "The Other", tuneId: 3, numRecordings: 100, numTunebooks: 2711,  names: [name1])
        tune2.save(flush:true)
        name1.tune = [tune1, tune2]
        expect:
            name1.tune.size() == 2
    }

    void "Check comparison"() {
        def name1 = new Name(name:'peter',index:1)
        def name2 = new Name(name:'paul',index:2)
        def name3 = new Name(name:'mary',index:3)
        expect:
        name1.compareTo(name1) == 0
        name1.compareTo(name2) < 0
        name1.compareTo(name3) < 0
        name2.compareTo(name1) > 0
        name2.compareTo(name2) == 0
        name2.compareTo(name3) < 0
        name3.compareTo(name1) > 0
        name3.compareTo(name2) > 0
        name3.compareTo(name3) == 0

    }

}
