package cscie56.finalproj


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
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
        expect:
        measure.validate()
    }

    void "create a simple measure with 1000"() {
        when: (" create a measure")
        List<Integer> seq = new ArrayList<>()
        for(i in 1..1000) {
            seq.add(i)
        }
        def measure = new Measure(sequence: seq)
        expect:
        measure.validate()
    }


    void "create a simple measure with 0"() {
        when: (" create a measure")
        List<Integer> seq = new ArrayList<>()
        def measure = new Measure(sequence: seq)
        expect:
        measure.validate()
    }


    void "create a simple measure with 1"() {
        when: (" create a measure")
        List<Integer> seq = new ArrayList<>()
        for(i in 1..1) {
            seq.add(i)
        }

        def measure = new Measure(sequence: seq)
        expect:
        measure.validate()
    }

}
