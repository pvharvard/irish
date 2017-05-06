package irish

import com.testapp.Role
import com.testapp.User
import com.testapp.UserRole
import cscie56.finalproj.Measure
import cscie56.finalproj.Name
import cscie56.finalproj.Tune
import cscie56.finalproj.TuneMeasureLoc
import cscie56.finalproj.Version

class BootStrap {

    enum InitSizeEnum {
        MINI, SMALL, LARGE
    }
    def nameMap = [:]
    def versionMap = [:]
    def tuneMap = [:]


    def InitSizeEnum INIT_CONFIG = InitSizeEnum.MINI


    def init = { servletContext ->
        if (INIT_CONFIG == InitSizeEnum.MINI) {
            initTunesMini()
            initUserRole()
        } else {
            initNames()
            initVersions()
            initTunesFile()
            versionMap = [:]
            initName2Tune()
            initUserRole()
        }

    }

    def destroy = {
    }

    def initTunesMini() {
        def name27a = new Name(name: 'Drowsy Maggie', index: 1)
        saveObject(name27a)
        def name27b = new Name(name: 'Drowsie Maggie', index: 2)
        saveObject(name27b)
        def name27c = new Name(name: 'Drowsy Maggy', index: 3)
        saveObject(name27c)
        def name27d = new Name(name: 'Maggie Tuirsearch', index: 4)
        saveObject(name27d)
        def name27e = new Name(name: 'Maggie\'s Drowsy', index: 5)
        saveObject(name27e)

        def name55a = new Name(name: 'The Kesh', index: 6)
        saveObject(name55a)
        def name55b = new Name(name: 'The Castle', index: 7)
        saveObject(name55b)
        def name55c = new Name(name: 'The Kesh Mountain', index: 8)
        saveObject(name55c)
        def name55d = new Name(name: 'The Kincora', index: 9)
        saveObject(name55d)
        def name55e = new Name(name: 'The Mountaineer\'s March', index: 10)
        saveObject(name55e)

        def name8a = new Name(name: 'Around the World', index: 11)
        saveObject(name8a)
        def name8b = new Name(name: 'The Bansee', index: 12)
        saveObject(name8b)
        def name8c = new Name(name: 'Bean An Mulga Sig', index: 13)
        saveObject(name8c)
        def name8d = new Name(name: 'The Bean Si', index: 14)
        saveObject(name8d)
        def name8e = new Name(name: 'Bean Sidhe', index: 15)
        saveObject(name8e)
        def name8f = new Name(name: 'James McMahon\'s', index: 16)
        saveObject(name8f)
        def name8g = new Name(name: 'Kilcaven Banks', index: 17)
        saveObject(name8g)
        def name8h = new Name(name: 'The Kilcaven Banks', index: 18)
        saveObject(name8h)
        def name8i = new Name(name: 'MacMahon\'s', index: 19)
        saveObject(name8i)
        def name8j = new Name(name: 'McMahon\'s', index: 20)
        saveObject(name8j)
        def name8k = new Name(name: 'Miss McMahon\'s', index: 21)
        saveObject(name8k)

        def name0 = new Name(name: 'Generic Test', index: 0)
        saveObject(name0)


        def version55_10 = new Version(setting: 26404, key: "Gmaj", meter: '6/8', unitLength: '1/8', index: 10,
                abc: '|:BGG dGG | ege dBA | BGG dGG | ~A3 ABc |\n' +
                        'BGG dGG | ege dBd | gbg aga | bgg g2d :|\n' +
                        '|:G2F GAB | A2G ABd | edd gdd | edB dBA |\n' +
                        'G2F GAB | A2G ABd | edd gdB |1 AGF GBA :|2 AGF G2A |')
        saveObject(version55_10)

        def version55_1 = new Version(setting: 55, key: "Gmaj", meter: '6/8', unitLength: '1/8', index: 1,
                abc: '|:G3 GAB| A3 ABd|edd gdd|edB dBA|\n' +
                        'GAG GAB|ABA ABd|edd gdd|BAF G3:|\n' +
                        'B2B d2d|ege dBA|B2B dBG|ABA AGA|\n' +
                        'BAB d^cd|ege dBd|gfg aga| bgg g3:|')
        saveObject(version55_1)

        def version55_5 = new Version(setting: 23498, key: "Dmaj", meter: '6/8', unitLength: '1/8', index: 5,
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

        TuneMeasureLoc tuneMeasureLoc1 = new TuneMeasureLoc(shift: 3, loc: 1)
        saveObject(tuneMeasureLoc1)
        TuneMeasureLoc tuneMeasureLoc2 = new TuneMeasureLoc(shift: 4, loc: 2)
        saveObject(tuneMeasureLoc2)

        def measure1 = new Measure(sequence: seq1, tuneMeasLoc: tuneMeasureLoc1)
        saveObject(measure1)
        def measure2 = new Measure(sequence: seq2, tuneMeasLoc: tuneMeasureLoc2)
        saveObject(measure2)

        def tune27 = new Tune(dance: Tune.Dance.REEL, primaryName: "Drowsey Maggie", tuneId: 27, numRecordings: 158, numTunebooks: 4932,
                names: [name27a, name27b, name27c, name27d, name27e, name0], tuneMeasLoc: [tuneMeasureLoc1, tuneMeasureLoc2])
        saveObject(tune27)

        def tune55 = new Tune(dance: Tune.Dance.JIG, primaryName: "The Kesh", tuneId: 55, numRecordings: 140, numTunebooks: 4463,
                names: [name55a, name55b, name55c, name55d, name55e, name0],
                versions: [version55_10, version55_1, version55_5],
                tuneMeasLoc: [tuneMeasureLoc2])
        saveObject(tune55)

        def tune8 = new Tune(dance: Tune.Dance.JIG, primaryName: "The Banshee", tuneId: 8, numRecordings: 100, numTunebooks: 2711,
                names: [name8a, name8b, name8c, name8d, name8e, name8f, name8g, name8h, name8i, name8j, name8k], tuneMeasLoc: [tuneMeasureLoc2])
        saveObject(tune8)


        name27a.tune = [tune27]
        saveObject(name27a)
        name27b.tune = [tune27]
        saveObject(name27b)
        name27c.tune = [tune27]
        saveObject(name27c)
        name27d.tune = [tune27]
        saveObject(name27d)
        name27e.tune = [tune27]
        saveObject(name27e)


        name55a.tune = [tune55]
        saveObject(name55a)
        name55b.tune = [tune55]
        saveObject(name55b)
        name55c.tune = [tune55]
        saveObject(name55c)
        name55d.tune = [tune55]
        saveObject(name55d)
        name55e.tune = [tune55]
        saveObject(name55e)


        name8a.tune = [tune8]
        saveObject(name8a)
        name8b.tune = [tune8]
        saveObject(name8b)
        name8c.tune = [tune8]
        saveObject(name8c)
        name8d.tune = [tune8]
        saveObject(name8d)
        name8e.tune = [tune8]
        saveObject(name8e)
        name8f.tune = [tune8]
        saveObject(name8f)
        name8g.tune = [tune8]
        saveObject(name8g)
        name8h.tune = [tune8]
        saveObject(name8h)
        name8i.tune = [tune8]
        saveObject(name8i)
        name8j.tune = [tune8]
        saveObject(name8j)
        name8k.tune = [tune8]
        saveObject(name8k)

        name0.tune = [tune27]
        name0.tune.add(tune55)
        saveObject(name0)
    }

    def initUserRole() {
        User admin = new User(username: 'admin', password: 'admin')
        saveObject(admin)
        User user = new User(username: 'user', password: 'user')
        saveObject(user)

        User peter = new User(username: 'peter', password: 'peter')
        saveObject(peter)

        Role adminRole = new Role(authority: Role.ROLE_ADMIN)
        saveObject(adminRole)
        Role userRole = new Role(authority: Role.ROLE_USER)
        saveObject(userRole)

        UserRole.create(admin, adminRole)
        UserRole.create(admin, userRole)
        UserRole.create(user, userRole)
        UserRole.create(peter, userRole)
    }


    def initNames() {
        def stream = grailsApplication.getParentContext().getResource("createNames2.txt").getInputStream()
        int lineNum = 0
        //println("Text has " + text.size() + " lines")
        stream.eachLine { line ->
            String[] tokens = line.split('\t')
            if (tokens[0].equals("name")) {
                lineNum += 1
                if (lineNum % 500 == 0) {
                    println("Name[" + lineNum + "]: " + tokens[1])
                }
                def oneName = new Name(name: tokens[1], index: lineNum)
                nameMap['name' + lineNum] = oneName
                saveObject(oneName)
            }
        }

    }

    def initVersions() {
        def stream = grailsApplication.getParentContext().getResource("versionBootstrap2.txt").getInputStream()
        int lineNum = 0
        //println("Text has " + text.size() + " lines")
        stream.eachLine { line ->
            String[] tokens = line.split('\t')
            if (tokens[0].equals("version")) {
                boolean chord = Boolean.parseBoolean(tokens[7])
                String abc = tokens[8].replaceAll("xxx[x]*", "\n")
                Version version = new Version(setting: tokens[2], key: tokens[3], meter: tokens[4], unitLength: tokens[5], index: tokens[6], chords: chord, abc: abc)
                if (lineNum % 500 == 0) {
                    println("Version[" + lineNum + "]: " + tokens[1])
                }
                versionMap[tokens[1]] = version
                saveObject(version)
            }
            lineNum += 1
        }

    }

    def initTunesFile() {
        def stream = grailsApplication.getParentContext().getResource("tuneBootstrap2.txt").getInputStream()
        int lineNum = 0
        println("Initializing tunes")
        //println("Text has " + text.size() + " lines")
        stream.eachLine { line ->
            String[] tokens = line.split('\t')
            if (tokens[0].equals("tune")) {
                def tuneId = tokens[1]
                def dance = Tune.Dance.REEL
                if (tokens[2].equals("JIG")) {
                    dance = Tune.Dance.JIG
                } else if (tokens[2].equals("POLKA")) {
                    dance = Tune.Dance.POLKA
                } else if (tokens[2].equals("SLIP_JIG")) {
                    dance = Tune.Dance.SLIP_JIG
                } else if (tokens[2].equals("WALTZ")) {
                    dance = Tune.Dance.WALTZ
                } else if (tokens[2].equals("SLIDE")) {
                    dance = Tune.Dance.SLIP_JIG
                } else if (tokens[2].equals("HORNPIPE")) {
                    dance = Tune.Dance.HORNPIPE
                }
                def versionList = []
                tokens[6].split("___").each {
                    versionList.add(versionMap[it])
                }
                def nameList = []
                tokens[7].split("___").each {
                    nameList.add(nameMap[it])
                }

                def oneTune = new Tune(tuneId: tuneId, dance: dance, primaryName: tokens[3], numRecordings: Integer.parseInt(tokens[4]), numTunebooks: Integer.parseInt(tokens[5]),
                        versions: versionList, names: nameList)
                tuneMap['tune' + tuneId] = oneTune
                if (lineNum % 100 == 0) {
                    println('Tune [' + lineNum + ']\t' + tuneId + ' ' + tokens[3])
                }

                saveObject(oneTune)
                lineNum += 1
            }
        }

    }

    def initName2Tune() {
        def stream = grailsApplication.getParentContext().getResource("name2tune2.txt").getInputStream()
        int lineNum = 0
        stream.eachLine { line ->
            if (lineNum % 25 == 0) {
                println("name2tune " + lineNum)
            }
            String[] tokens = line.split('\t')
            if (tokens[0].equals("name2tune")) {
                def tunes = []
                for (int tokeni = 2; tokeni < tokens.length; tokeni++) {
                    //println('\tTuneMap of ' + tokens[tokeni])
                    if (tuneMap.containsKey(tokens[tokeni])) {
                        //println('\t' + tuneMap[tokens[tokeni]])
                        tunes.add(tuneMap[tokens[tokeni]])
                    } else {
                        println('TuneMap doesnt have key ' + tokens[tokeni])
                    }
                }
                //println('Accessing nameMap ' + tokens[1])
                if (nameMap.containsKey('name' + tokens[1])) {
                    def oneName = nameMap['name' + tokens[1]]
                    oneName.tune = tunes
                    saveObject(oneName)
                } else {
                    println("NameMap doesn't have entry for name" + tokens[1])
                }
            }
            lineNum += 1
        }

    }


    def saveObject(object) {
        if (!object.save(flush: true)) {
            object.errors.allErrors.each { println it }
        }
    }
}
