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

        List<Integer> seq1 = new ArrayList<>()
        seq1.add(2)
        seq1.add(3)
        seq1.add(5)
        List<Integer> seq2 = new ArrayList<>()
        seq2.add(1)
        seq2.add(3)
        seq2.add(-5)

        TuneMeasureLoc tuneMeasureLoc1 = new TuneMeasureLoc(shift:3, loc:1)
        TuneMeasureLoc tuneMeasureLoc2 = new TuneMeasureLoc(shift:4, loc:2)
        def name8a = new Name(name: 'Around the World')
        def name8b = new Name(name: 'The Bansee')
        def name8c = new Name(name: 'Bean An Mulga Sig')
        def name8d = new Name(name: 'The Bean Si')


        def tune = new Tune(dance:Tune.Dance.REEL, primaryName:"Drowsey Maggie", tuneId:27, numRecordings: 158, numTunebooks: 4932,
                names: [name8a, name8b, name8c, name8d], tuneMeasLoc:[tuneMeasureLoc1, tuneMeasureLoc2])
        then:
        tune.validate()

    }

    void "Create a minimal tune"() {
        when:
        def tune = new Tune(dance:Tune.Dance.REEL, primaryName:"Drowsey Maggie", tuneId:27, numRecordings: 158, numTunebooks: 4932)
        then:
        tune.validate()

    }

    void "Create a generic tune with the nullable features"() {
        when:
        def tune = new Tune(primaryName:"Drowsey Maggie")
        then:
        ! tune.validate()

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
