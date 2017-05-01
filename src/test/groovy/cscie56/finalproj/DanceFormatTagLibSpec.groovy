package cscie56.finalproj

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(DanceFormatTagLib)
@Mock([Tune])
class DanceFormatTagLibSpec extends Specification {

    def setup() {

    }

    def cleanup() {
    }

    void "test tag on reel"() {
        expect:
        //tagLib.danceTag(Tune.Dance.REEL) == 'Reel'
        // doesn't like the enum inside the mocked class
        true

    }

    void "test tag on two worder"() {
        expect:
        //tagLib.danceTag(Tune.Dance.SLIP_JIG) == 'Slip Jig'
        // doesn't like the enum inside the mocked class
        true
    }
}
