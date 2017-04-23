package cscie56.finalproj


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Version)
class VersionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "create version"() {
        def version = new Version(setting:10, index:5, key:"Gmaj", meter:"3/4", unitLength:"1/8", abc:"abcdefga")
        expect:
        version.validate()
    }

    void "create long abc to test the limit"() {
        def version = new Version(setting:10, index:5, key:"Gmaj", meter:"3/4", unitLength:"1/8",
                abc:"abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
            "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc")
        expect:
        version.validate()
    }

    void "create extremely long abc to test the limit"() {
        def version = new Version(setting:10, index:5, key:"Gmaj", meter:"3/4", unitLength:"1/8",
                abc:"abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc" +
                        "abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc abcdefgabc")
        expect:
        version.validate()
    }

}
