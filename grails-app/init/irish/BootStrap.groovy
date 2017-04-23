package irish

import cscie56.finalproj.Measure
import cscie56.finalproj.Name
import cscie56.finalproj.Tune
import cscie56.finalproj.TuneMeasureLoc
import cscie56.finalproj.Version

class BootStrap {

    def init = { servletContext ->
        initTunes()

    }
    def destroy = {
    }

    def initTunes() {
        def name27a = new Name(name: 'Drowsy Maggie')
        saveObject(name27a)
        def name27b = new Name(name: 'Drowsie Maggie')
        saveObject(name27b)
        def name27c = new Name(name: 'Drowsy Maggy')
        saveObject(name27c)
        def name27d = new Name(name: 'Maggie Tuirsearch')
        saveObject(name27d)
        def name27e = new Name(name: 'Maggie\'s Drowsy')
        saveObject(name27e)

        def name55a = new Name(name: 'The Kesh')
        saveObject(name55a)
        def name55b = new Name(name: 'The Castle')
        saveObject(name55b)
        def name55c = new Name(name: 'The Kesh Mountain')
        saveObject(name55c)
        def name55d = new Name(name: 'The Kincora')
        saveObject(name55d)
        def name55e = new Name(name: 'The Mountaineer\'s March')
        saveObject(name55e)

        def name8a = new Name(name: 'Around the World')
        saveObject(name8a)
        def name8b = new Name(name: 'The Bansee')
        saveObject(name8b)
        def name8c = new Name(name: 'Bean An Mulga Sig')
        saveObject(name8c)
        def name8d = new Name(name: 'The Bean Si')
        saveObject(name8d)
        def name8e = new Name(name: 'Bean Sidhe')
        saveObject(name8e)
        def name8f = new Name(name: 'James McMahon\'s')
        saveObject(name8f)
        def name8g = new Name(name: 'Kilcaven Banks')
        saveObject(name8g)
        def name8h = new Name(name: 'The Kilcaven Banks')
        saveObject(name8h)
        def name8i = new Name(name: 'MacMahon\'s')
        saveObject(name8i)
        def name8j = new Name(name: 'McMahon\'s')
        saveObject(name8j)
        def name8k = new Name(name: 'Miss McMahon\'s')
        saveObject(name8k)


        def version55_10 = new Version(setting:26404, key:"Gmaj", meter:'6/8', unitLength : '1/8', index:10,
                abc:'|:BGG dGG | ege dBA | BGG dGG | ~A3 ABc |\n' +
                        'BGG dGG | ege dBd | gbg aga | bgg g2d :|\n' +
                        '|:G2F GAB | A2G ABd | edd gdd | edB dBA |\n' +
                        'G2F GAB | A2G ABd | edd gdB |1 AGF GBA :|2 AGF G2A |')
        saveObject(version55_10)

        def version55_1 = new Version(setting:55, key:"Gmaj", meter:'6/8', unitLength : '1/8',index:1,
            abc: '|:G3 GAB| A3 ABd|edd gdd|edB dBA|\n' +
            'GAG GAB|ABA ABd|edd gdd|BAF G3:|\n' +
            'B2B d2d|ege dBA|B2B dBG|ABA AGA|\n' +
            'BAB d^cd|ege dBd|gfg aga| bgg g3:|')
        saveObject(version55_1)

        def version55_5 = new Version(setting:23498, key:"Dmaj", meter :'6/8', unitLength : '1/8', index:5,
                abc: 'A|:D3 DEF | E3 EFA | BAA dAA | BAF AFE |\n' +
                'D3 DEF | E3 EFA | BAA dAF | EFE D3 :||\n' +
                '|: F3 AFA | (3BcdB AFD |F3 AFD | EFE EDE |\n' +
                'F3 AFA | (3BcdB AFA | d(3Bcd ede | fdc d3 :||')
        saveObject(version55_5)

        List<Integer> seq1 = new ArrayList<>()
        seq1.add(2)
        seq1.add(3)
        seq1.add(5)
        List<Integer> seq2 = new ArrayList<>()
        seq2.add(1)
        seq2.add(3)
        seq2.add(-5)

        TuneMeasureLoc tuneMeasureLoc1 = new TuneMeasureLoc(shift:3, loc:1)
        saveObject(tuneMeasureLoc1)
        TuneMeasureLoc tuneMeasureLoc2 = new TuneMeasureLoc(shift:4, loc:2)
        saveObject(tuneMeasureLoc2)

        def measure1 = new Measure(sequence: seq1, tuneMeasLoc:tuneMeasureLoc1)
        saveObject(measure1)
        def measure2 = new Measure(sequence: seq2, tuneMeaLoc:tuneMeasureLoc2)
        saveObject(measure2)

        def tune27 = new Tune(dance:Tune.Dance.REEL, primaryName:"Drowsey Maggie", tuneId:27, numRecordings: 158, numTunebooks: 4932,
                names: [name27a, name27b, name27c, name27d, name27e], tuneMeasLec:[tuneMeasureLoc1, tuneMeasureLoc2])
        saveObject(tune27)

        def tune55 = new Tune(dance:Tune.Dance.JIG, primaryName:"The Kesh", tuneId:55, numRecordings: 140, numTunebooks: 4463,
                names: [name55a, name55b, name55c, name55d, name55e],
                versions: [version55_10, version55_1, version55_5],
                tuneMeasLec:[tuneMeasureLoc2])
        saveObject(tune55)

        def tune8 = new Tune(dance:Tune.Dance.JIG, primaryName:"The Banshee", tuneId:8, numRecordings: 100, numTunebooks: 2711,
                names: [name8a, name8b, name8c, name8d, name8e, name8f, name8g, name8h, name8i, name8j, name8k])
        saveObject(tune8)


    }


    def saveObject(object) {
        if (!object.save(flush:true)) {
            object.errors.allErrors.each { println it }
        }
    }
}
