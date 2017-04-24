package cscie56.finalproj

class Measure {
    List<Integer> sequence

    static constraints = {
        //sequence unique: true
    }

    static hasMany = { tuneMeasLoc : TuneMeasureLoc }
}
