package cscie56.finalproj

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
    }

    static hasMany = [names : Name, versions : Version, setMate : SetMate, tuneMeasLoc : TuneMeasureLoc]

}
