package cscie56.finalproj

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@Mock([Tune,TuneMeasureLoc])
@TestFor(Measure)
class MeasureSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "create a simple measure with 10"() {
        when: (" create a measure")
            List<Integer> seq = new ArrayList<>()
            for(i in 0..10) {
                seq.add(i)
            }
            def measure = new Measure(sequence: seq)
        then:
        measure.validate()
    }

    void "create a simple measure with 1000"() {
        when: (" create a measure")
        List<Integer> seq = new ArrayList<>()
        for(i in 1..1000) {
            seq.add(i)
        }
        def measure = new Measure(sequence: seq)
        then:
        measure.validate()
    }


    void "create a simple measure with 0"() {
        when: (" create a measure")
        List<Integer> seq = new ArrayList<>()
        def measure = new Measure(sequence: seq)
        then:
        measure.validate()
    }


    void "create a simple measure with 1"() {
        when: (" create a measure")
        List<Integer> seq = new ArrayList<>()
        for(i in 1..1) {
            seq.add(i)
        }

        def measure = new Measure(sequence: seq)
        then:
        measure.validate()
    }

    void "test multiple tunes"() {
        List<Integer> seq = new ArrayList<>()
        Measure measure = new Measure(sequence: seq)
        def tune1 = new Tune(dance: Tune.Dance.JIG, primaryName: "The Banshee", tuneId: 8, numRecordings: 100, numTunebooks: 2711)
        tune1.save(flush:true)
        def tune2 = new Tune(dance: Tune.Dance.JIG, primaryName: "The Other", tuneId: 3, numRecordings: 100, numTunebooks: 2711)
        tune2.save(flush:true)


        def tml1 = new TuneMeasureLoc(loc:1, shift:0, tune:tune1, measure:measure)
        def tml2 = new TuneMeasureLoc(loc:1, shift:2, tune:tune2, measure:measure)
        measure.tuneMeasLoc = [tune1, tune2]
        tune1.tuneMeasLoc = [tml1]
        tune2.tuneMeasLoc = [tml2]
        expect:
        measure.tuneMeasLoc.size() == 2

    }

}
