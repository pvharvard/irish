package cscie56.finalproj

class Version {
    Integer setting
    String key
    String meter
    String unitLength

    static constraints = {
    }

    static belongsTo = {tune : Tune}
}
