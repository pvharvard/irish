package cscie56.finalproj

class DanceFormatTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    /**
     * @attr dance required
     */
    def danceTag = { attrs ->
        def dance = attrs.dance
        if(dance == Tune.Dance.JIG) {
            out << "Jig"
        } else if(dance == Tune.Dance.REEL) {
            out << "Reel"
        } else if(dance == Tune.Dance.SLIP_JIG) {
            out << "Slip Jig"
        } else if(dance == Tune.Dance.SLIDE) {
            out << "Slide"
        } else if(dance == Tune.Dance.POLKA) {
            out << "Polka"
        } else if(dance == Tune.Dance.HORNPIPE) {
            out << "Hornpipe"
        } else if(dance == Tune.Dance.WALTZ) {
            out << "Waltz"
        } else {
            out << "Unknown"
        }
    }
}
