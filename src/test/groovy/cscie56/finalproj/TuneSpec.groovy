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

        TuneMeasureLoc tuneMeasureLoc1 = new TuneMeasureLoc(shift: 3, loc: 1)
        TuneMeasureLoc tuneMeasureLoc2 = new TuneMeasureLoc(shift: 4, loc: 2)
        def name8a = new Name(name: 'Around the World')
        def name8b = new Name(name: 'The Bansee')
        def name8c = new Name(name: 'Bean An Mulga Sig')
        def name8d = new Name(name: 'The Bean Si')


        def tune = new Tune(dance: Tune.Dance.REEL, primaryName: "Drowsey Maggie", tuneId: 27, numRecordings: 158, numTunebooks: 4932,
                names: [name8a, name8b, name8c, name8d], tuneMeasLoc: [tuneMeasureLoc1, tuneMeasureLoc2])
        then:
        tune.validate()

    }

    void "Create a minimal tune"() {
        when:
        def tune = new Tune(dance: Tune.Dance.REEL, primaryName: "Drowsey Maggie", tuneId: 27, numRecordings: 158, numTunebooks: 4932)
        then:
        tune.validate()

    }

    void "Create a generic tune with the nullable features"() {
        when:
        def tune = new Tune(primaryName: "Drowsey Maggie")
        then:
        !tune.validate()

    }

    void "Verify the uniqueness is being met"() {
        when:
        int tuneId1 = 100
        int tuneId2 = 100
        def tune1 = new Tune(primaryName: "tune1", tuneId: tuneId1)
        tune1.save(flush: true)
        def tune2 = new Tune(primaryName: "tune2", tuneId: tuneId2)
        then:
        !tune2.validate()

    }

    void "test toString"() {
        def tune1 = new Tune(primaryName: 'primaryName', tuneId: 100)
        expect:
        tune1.toString() == 'Tune[100] primaryName'
    }


    void "test getVersion1Abc"() {
        when:
        String v1abc = "a|abcd|efga|bCDE|fGa2|bgae|"
        String v2abc = "aaab|bbbc|cccd|ddde|eeef|bgae|eeeef|ffffg|"
        def v2 = new Version(index: 2, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v2abc)
        def v1 = new Version(index: 1, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v1abc)
        def tune1 = new Tune(primaryName: 'primaryName', tuneId: 100, versions: [v1, v2])
        then:
        tune1.getVersion1Abc() == v1abc
    }

    void "test getVersion1StartAbc"() {
        when:
        String v1abc = "a|abcd|efga|bCDE|fGa2|bgae|"
        String v2abc = "aaab|bbbc|cccd|ddde|eeef|bgae|eeeef|ffffg|"
        def v2 = new Version(index: 2, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v2abc)
        def v1 = new Version(index: 1, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v1abc)
        def tune1 = new Tune(primaryName: 'primaryName', tuneId: 100, versions: [v1, v2])
        then:
        tune1.getVersion1StartAbc() == "a|abcd|efga|\n"
    }

    void "test getVersion1KeyMeter"() {
        when:
        String v1abc = "a|abcd|efga|bCDE|fGa2|bgae|"
        String v2abc = "aaab|bbbc|cccd|ddde|eeef|bgae|eeeef|ffffg|"
        def v2 = new Version(index: 2, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v2abc)
        def v1 = new Version(index: 1, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v1abc)
        def tune1 = new Tune(primaryName: 'primaryName', tuneId: 100, versions: [v1, v2])
        then:
        tune1.getVersion1KeyMeter() == "K: Gmaj\nM: 4/4\n"
    }

    void "test getVersion1Abc with no version 1"() {
        when:
        String v1abc = "a|abcd|efga|bCDE|fGa2|bgae|"
        String v2abc = "aaab|bbbc|cccd|ddde|eeef|bgae|eeeef|ffffg|"
        def v2 = new Version(index: 2, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v2abc)
        def v3 = new Version(index: 3, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v1abc)
        def tune1 = new Tune(primaryName: 'primaryName', tuneId: 100, versions: [v3, v2])
        then:
        tune1.getVersion1Abc() == ""
    }

    void "test getVersion1StartAbc  with no version 1"() {
        when:
        String v1abc = "a|abcd|efga|bCDE|fGa2|bgae|"
        String v2abc = "aaab|bbbc|cccd|ddde|eeef|bgae|eeeef|ffffg|"
        def v2 = new Version(index: 2, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v2abc)
        def v3 = new Version(index: 3, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v1abc)
        def tune1 = new Tune(primaryName: 'primaryName', tuneId: 100, versions: [v3, v2])
        then:
        tune1.getVersion1StartAbc() == ""
    }

    void "test getVersion1KeyMeter with no version 1"() {
        when:
        String v1abc = "a|abcd|efga|bCDE|fGa2|bgae|"
        String v2abc = "aaab|bbbc|cccd|ddde|eeef|bgae|eeeef|ffffg|"
        def v2 = new Version(index: 2, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v2abc)
        def v3 = new Version(index: 3, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v1abc)
        def tune1 = new Tune(primaryName: 'primaryName', tuneId: 100, versions: [v3, v2])
        then:
        tune1.getVersion1KeyMeter() == ""
    }

    void "test many names and versions"() {
        when:
        String v1abc = "a|abcd|efga|bCDE|fGa2|bgae|"
        String v2abc = "aaab|bbbc|cccd|ddde|eeef|bgae|eeeef|ffffg|"
        def v2 = new Version(index: 2, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v2abc)
        def v3 = new Version(index: 3, setting: 1, key: 'Gmaj', meter: '4/4', unitLength: '1/8', abc:v1abc)
        def n1 = new Name(name:'name1', index:1)
        def n2 = new Name(name:'name2', index:2)
        def n3 = new Name(name:'name3', index:3)
        def tune1 = new Tune(primaryName: 'primaryName', tuneId: 100, versions: [v3, v2], names:[n1,n2,n3])
        then:
        tune1.names.size() == 3
        tune1.versions.size() == 2
    }

}