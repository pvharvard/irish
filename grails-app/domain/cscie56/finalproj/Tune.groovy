package cscie56.finalproj

class Tune {
    enum Dance {JIG, REEL, HORNPIPE, WALTZ, POLKA, SLIDE, SLIP_JIG }

    Integer tuneId
    Integer numTunebooks
    Integer numRecordings
    Dance dance


    static constraints = {
        tuneId unique : true, blank : true
        numTunebooks blank : true
        numRecordings blank : true
        dance blank : true
    }

    static hasMany = [name : Name, version : Version, setMate : SetMate]
}
