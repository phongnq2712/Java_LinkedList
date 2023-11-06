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

        return new Participant(newParticipantID, this.participantName, this.candyCollection.clone());
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
        return "Participant{" +
                "participantID='" + participantID + '\'' +
                ", participantName='" + participantName + '\'' +
                ", candyCollection=" + Arrays.toString(candyCollection) +
                '}';
    }
    @Override
    public boolean isInTheTopThree(Participant p) {
        return false;
    }
}
