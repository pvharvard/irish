package cscie56.finalproj

class TuneMeasureLoc {
    Integer loc
    Integer shift
    Tune tune
    Measure measure

    static constraints = {
        tune nullable: true
        measure nullable:true
    }
}
