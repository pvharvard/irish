package cscie56.finalproj

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@Mock([TuneMeasureLoc, Name])
@TestFor(Tune)
class TuneSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }


    void "Create a generic tune"() {
        when:
        def tune = new Tune(dance:Tune.Dance.REEL, primaryName:"Drowsey Maggie", tuneId:27, numRecordings: 158, numTunebooks: 4932,
                names: [name27a, name27b, name27c, name27d, name27e], tuneMeasLec:[tuneMeasureLoc1, tuneMeasureLoc2])
        then:
        tune.validate()

    }

    void "Create a generic tune with the nullable features"() {
        when:
        def tune = new Tune(primaryName:"Drowsey Maggie")
        then:
        tune.validate()

    }

    void "Verify the uniqueness is being met"() {
        when:
        int tuneId1 = 100
        int tuneId2 = 100
        def tune1 = new Tune(primaryName:"tune1", tuneId: tuneId1)
        tune1.save(flush:true)
        def tune2 = new Tune(primaryName:"tune2", tuneId: tuneId2)
        then:
        ! tune2.validate()

    }



}
