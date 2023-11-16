public class ParticipantGroup {

    private ParticipantList topThree;

    private ParticipantList rest;

    public ParticipantGroup(ParticipantList topThree, ParticipantList rest) {
        this.topThree = topThree;
        this.rest = rest;
    }

    public ParticipantList getTopThree() {
        return topThree;
    }

    public ParticipantList getRest() {
        return rest;
    }
}
