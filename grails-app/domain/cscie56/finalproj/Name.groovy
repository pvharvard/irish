package cscie56.finalproj

class Name implements Comparable {
    String name
    Integer index

    static constraints = {

    }

    static belongsTo = [tune : Tune]

    int compareTo(def other) {
        return (Integer) this.index <=> (Integer) other.index
    }
}
