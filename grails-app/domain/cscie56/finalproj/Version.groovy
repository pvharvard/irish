package cscie56.finalproj

class Version implements Comparable {
    Integer setting
    Integer index
    String key
    String meter
    String unitLength
    String abc

    static constraints = {
        abc(maxSize:2000)
    }

    static transients = ['startAbc']

    int compareTo(def other) {
        return this.index <=> other?.index
    }

    /**
     * Returns the first 3 measures as ABC ignoring the header
     * @return
     */
    String getStartAbc() {
        String[] splits = abc.split("\\|");
        int len = 3;
        StringBuilder builder = new StringBuilder();
        //builder.append('X:1\n')
        if(abc.startsWith("|")) {
            len++;
            //builder.append("|")
        }
        for(int i = 0; i < Math.min(len, splits.length); i++) {
            builder.append(splits[i])
            builder.append("|")
        }
        builder.append('\n')
        return builder.toString();
    }
}
