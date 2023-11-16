import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Assignment 2
 * © Quoc Phong Ngo
 * Written by: Quoc Phong Ngo - 40230574
 * Due Date: Nov 23, 2023
 */
public class Participant implements Winable {
    private String participantID;

    private String participantName;

    private int[] candyCollection;

    private float monetaryValue;

    private int utilityValue;

    private ParticipantGroup participantGroup;

    public Participant(String participantID, String participantName, int[] candyCollection) {
        this.participantID = participantID;
        this.participantName = participantName;
        this.candyCollection = candyCollection;
    }

    public Participant(Participant participant, String newParticipantID) {
        this.participantID = newParticipantID;
        this.participantName = participant.participantName;
        this.candyCollection = participant.candyCollection.clone();
    }

    public Participant clone() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new participant ID for cloning: ");
        String newParticipantID = scanner.nextLine();
        if(!newParticipantID.isEmpty()) {
            return new Participant(newParticipantID, this.participantName, this.candyCollection.clone());
        }

        return null;
    }
    public String getParticipantID() {
        return participantID;
    }

    public void setParticipantID(String participantID) {
        this.participantID = participantID;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public int[] getCandyCollection() {
        return candyCollection;
    }

    public void setCandyCollection(int[] candyCollection) {
        this.candyCollection = candyCollection;
    }

    public float getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(float monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

    public int getUtilityValue() {
        return utilityValue;
    }

    public void setUtilityValue(int utilityValue) {
        this.utilityValue = utilityValue;
    }

    public ParticipantGroup getParticipantGroup() {
        return participantGroup;
    }

    public void setParticipantGroup(ParticipantGroup participantGroup) {
        this.participantGroup = participantGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(participantName, that.participantName) && Arrays.equals(candyCollection, that.candyCollection);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(participantName);
        result = 31 * result + Arrays.hashCode(candyCollection);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "participantID='" + participantID + '\'' +
                ", participantName='" + participantName + '\'' +
                ", candyCollection=" + Arrays.toString(candyCollection) +
                '}';
    }

    /**
     * The method isInTheTopThree
     * that takes in another Participant object P and should return true if P is from the
     * same group as the current participant object, or vise versa; otherwise it returns false
     * @param p
     * @return
     */
    @Override
    public boolean isInTheTopThree(Participant p) {
        boolean currentIsInTopThree = this.participantGroup.getTopThree().contains(this.getParticipantID(), false);
        boolean paramIsInTopThree = p.participantGroup.getTopThree().contains(p.getParticipantID(), false);

        return currentIsInTopThree && paramIsInTopThree;
    }

    public void calculateValues(Participant participant, ArrayList<Candy> candies) {
        float totalMonetary = 0;
        int totalUtility = 0;
        int[] candyCollection = participant.getCandyCollection();
        for(int i = 0; i < candyCollection.length; i++) {
            totalMonetary += candyCollection[i] * candies.get(i).getMonetaryValue();
            participant.setMonetaryValue(totalMonetary);
            totalUtility += candyCollection[i] * candies.get(i).getUtilityValue();
            participant.setUtilityValue(totalUtility);
        }
        System.out.println(participant.getParticipantName() + ": " + totalMonetary + "--" + totalUtility);
    }
}
