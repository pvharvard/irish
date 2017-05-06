package cscie56.finalproj

import com.testapp.Role

class Tune {
    enum Dance {JIG, REEL, HORNPIPE, WALTZ, POLKA, SLIDE, SLIP_JIG }

    Integer tuneId
    Integer numTunebooks
    Integer numRecordings
    Dance dance
    String primaryName


    static constraints = {
        tuneId unique : true, blank : true
        numTunebooks blank : true
        numRecordings blank : true
        dance blank : true
        tuneMeasLoc nullable : true
        setMate nullable : true
        names nullable : true
        versions nullable : true

    }

    static belongsTo = Tune

    static hasMany = [names : Name, versions : Version, setMate : SetMate, tuneMeasLoc : TuneMeasureLoc]

    static transients  = ['version1StartAbc', 'version1KeyMeter', 'version1Abc']

    def getVersion1StartAbc() {
        def abc
        versions.each {
            if(it.index==1) {
                abc = it.startAbc
            }
        }
        abc
    }

    def getVersion1KeyMeter() {
        def keyMeter
        versions.each {
            if(it.index==1) {
                keyMeter = 'K: ' + it.key + '\nM: ' + it.meter + '\n'
            }
        }
        keyMeter
    }

    def getVersion1Abc() {
        def abc
        versions.each {
            if(it.index==1) {
                abc = it.abc
            }
        }
        abc
    }


    String toString() {
        return "Tune[" + tuneId + "] " + primaryName
    }



}
