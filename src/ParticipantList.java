import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Assignment 2
 * © Quoc Phong Ngo
 * Written by: Quoc Phong Ngo - 40230574
 * Due Date: Nov 23, 2023
 */
public class ParticipantList {
    private class ParticipantNode {
        private Participant participant;
        private ParticipantNode next;

        public ParticipantNode() {
            this.participant = null;
            this.next = null;
        }

        public ParticipantNode(Participant participant, ParticipantNode next) {
            this.participant = participant;
            this.next = next;
        }

        public ParticipantNode(ParticipantNode otherNode) {
            this.participant = otherNode.participant;
            this.next = otherNode.next;
        }

        public ParticipantNode clone() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the new participant ID for cloning: ");
            String newParticipantID = scanner.nextLine();
            Participant participant = new Participant(this.participant, newParticipantID);

            return new ParticipantNode(participant, this.next);
        }
        public Participant getParticipant() {
            return participant;
        }

        public void setParticipant(Participant participant) {
            this.participant = participant;
        }

        public ParticipantNode getNext() {
            return next;
        }

        public void setNext(ParticipantNode next) {
            this.next = next;
        }
    }
    private ParticipantNode head;
    private int size;

    public ParticipantList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public ParticipantList(ParticipantList participantList) {
        this.head = null;
        this.size = 0;
        if(participantList != null) {
            this.head = new ParticipantNode(participantList.head);

            ParticipantNode newNode = this.head;
            ParticipantNode otherNode = participantList.head;

            while(otherNode.getNext() != null) {
                newNode.setNext(new ParticipantNode(otherNode.getNext()));
                newNode = newNode.getNext();
            }
            this.size = participantList.size;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Insert the node at the head of the list
     * @param participant
     */
    public void addToStart(Participant participant) {
        ParticipantNode newNode = new ParticipantNode();
        newNode.setParticipant(participant);
        newNode.setNext(this.head);
        this.head = newNode;
        this.size ++;
    }

    /**
     * Insert the node at the selected index
     * @param participant
     * @param index
     */
    public void insertAtIndex(Participant participant, int index) {
        if(index < 0 || index >= this.size) {
            throw new NoSuchElementException("Invalid Index");
        }
        if(index == 0) {
            addToStart(participant);
        } else {
            // get the previous node of the new node
            ParticipantNode previousNode = getNodeAtIndex(index - 1);
            if(previousNode != null) {
                ParticipantNode newNode = new ParticipantNode();
                newNode.setParticipant(participant);
                newNode.setNext(previousNode.getNext());
                previousNode.setNext(newNode);

                this.size ++;
            } else {
                throw new NoSuchElementException("Invalid Index");
            }
        }
    }

    /**
     * Delete the node with selected index
     * @param index
     * @return
     */
    public ParticipantNode deleteFromIndex(int index) {
        // check whether the list is empty or not
        if(isEmpty()) {
            System.out.println("List is empty!");

            return null;
        }
        if(index < 0 || index >= size) {
            throw new NoSuchElementException("Invalid Index");
        }
        if(index == 0) {
            deleteFromStart();
        } else {
            // get the previous node of the removed node
            ParticipantNode previousNode = getNodeAtIndex(index - 1);
            if(previousNode != null) {
                ParticipantNode removedNode = previousNode.getNext();
                previousNode.setNext(removedNode.getNext());
                removedNode.setNext(null);
                this.size --;

                return removedNode;
            } else {
                throw new NoSuchElementException("Invalid Index");
            }
        }

        return null;
    }

    /**
     * Get the node by a given index
     * @param index
     * @return
     */
    private ParticipantNode getNodeAtIndex(int index) {
        if(isEmpty()) {
            System.out.println("List is empty!");

            return null;
        }
        if(index < 0 || index >= size) {
            throw new NoSuchElementException("Invalid Index");
        }
        ParticipantNode currentNode = head;
        int currentIndex = 0;
        while(currentIndex < index && currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
            currentIndex ++;
        }

        return currentNode;
    }

    /**
     * Delete the first node in the list
     * @return
     */
    public ParticipantNode deleteFromStart() {
        // check whether the list is empty or not
        if(isEmpty()) {
            System.out.println("List is empty!");

            return null;
        }
        ParticipantNode removedNode = this.head;
        this.head = this.head.getNext();
        this.size --;
        removedNode.setNext(null);

        return removedNode;
    }

    /**
     * Object in list at passed index must be replaced with the object passed
     * with the object passed
     * @param participant
     * @param index
     */
    public void replaceAtIndex(Participant participant, int index) {
        // check whether the list is empty or not
        if(isEmpty()) {
            System.out.println("List is empty!");

            return;
        }
        if(index < 0 || index >= size) {
            throw new NoSuchElementException("Invalid Index");
        }

        ParticipantNode oldNode = getNodeAtIndex(index);
        if(oldNode != null) {
            oldNode.setParticipant(participant);
        } else {
            throw new NoSuchElementException("Invalid index");
        }

    }

    /**
     * Searching the list for a participantNode with that participantID
     * @param participantID
     * @return
     */
    public ParticipantNode find(String participantID) {
        if(isEmpty()) {
            System.out.println("List is empty!");

            return null;
        }
        // keep track the number of iterations
        int count = 0;
        ParticipantNode currentNode = this.head;
        while(currentNode != null) {
            count ++;
            if(participantID.equals(currentNode.getParticipant().getParticipantID())) {
                return currentNode;
            }
            // move on to the next node
            currentNode = currentNode.getNext();
        }

        return null;
    }

    /**
     *
     * @param participantID
     * @return
     */
    public boolean contains(String participantID) {

        return true;
    }

    /**
     *
     * @param participantList
     */
    public void equals(ParticipantList participantList) {

    }

    /**
     * Print the list
     */
    public void printList() {
        ParticipantNode current = this.head;
        System.out.println("HEAD ->");
        while(current != null) {
            System.out.println(current);
            System.out.println(" -> ");
            System.out.println(current.getNext());
            current = current.getNext();
        }
        System.out.println("NULL");
    }
}
