import java.util.*;

/**
 * Assignment 2
 * © Quoc Phong Ngo
 * Written by: Quoc Phong Ngo - 40230574
 * Due Date: Nov 23, 2023
 */

public class CompetitionResults {
    public static void main(String[] args) {
        System.out.println("Welcome, Quoc Phong Ngo!");
        Scanner scanner = new Scanner(System.in);
        // a & b. Input participant information as depicted in Figure 1 to initialize the
        // ParticipantList objects
        System.out.println("---------------Enter Participant Information--------------");
        System.out.print("Enter number of participants: ");
        int numberOfParticipants = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter ParticipantName and number of each type of candy collected: (separated by a space)");
        System.out.println("For example: Shadowcaster 7 5 4 5 6 7 2 1 0 9");
        ParticipantList pList1 = new ParticipantList();
        for(int i = 0; i < numberOfParticipants; i++) {
            String input = scanner.nextLine();
            String[] inputArr = input.split("\\s+");
            if(inputArr.length > 0) {
                String participantName = inputArr[0];
                int[] candies = new int[inputArr.length - 1];
                for(int cnt = 0; cnt < inputArr.length - 1; cnt++) {
                    candies[cnt] = Integer.parseInt(inputArr[cnt + 1]);
                }
                Participant participant = new Participant("P" + (i+1), participantName, candies);
                // each record is inserted in the list only once.
                pList1.addToStart(participant);
                checkDuplicate(pList1, participant);
            }
        }

        System.out.println("----------------------------------------------------");
        System.out.println("******* List 1: *******");
        pList1.printList();

        // Checking copy constructor III (e)
        ParticipantList pList2 = new ParticipantList(pList1);
        System.out.println("----------------------------------------------------");
        System.out.println("******* List 2: *******");
        pList2.printList();

        // c. print the outcome whether the participant will be in top three or not
        System.out.println("---------------Enter Request Information--------------");
        ArrayList<String> requests = new ArrayList<>();
        while(true) {
            System.out.print("Enter the participant name (leave blank to stop): ");
            String participantName = scanner.nextLine();
            if(participantName.equalsIgnoreCase("")) {
                break;
            }
            requests.add(participantName);
        }

        ArrayList<Participant> participantArray = pList2.toArrayList();

        ArrayList<Candy> candies = new ArrayList<>();
        candies.add(new Candy(1, "Fizzleberry Firecrunch", 2.0f, 8));
        candies.add(new Candy(2, "Goblin Gummy Grins", 1.5f, 7));
        candies.add(new Candy(3, "Moonlight Swirls", 1.75f, 9));
        candies.add(new Candy(4, "Witch's Brew Bonbons", 2.5f, 6));
        candies.add(new Candy(5, "Starlight Sugar Sprinkles", 1.25f, 7));
        candies.add(new Candy(6, "Dragon Scale Delights", 2.25f, 8));
        candies.add(new Candy(7, "Unicorn Rainbow Drops", 1.75f, 9));
        candies.add(new Candy(8, "Mystic Marshmallows", 1.5f, 7));
        candies.add(new Candy(9, "Enchanted Chocolate Truffles", 2.0f, 9));
        candies.add(new Candy(10, "Fairy Feathers", 1.25f, 9));

        // create an array list from the input information
        ArrayList<Participant> newParticipantList = new ArrayList<>();
        for(String pName : requests) {
            for(Participant participant : participantArray) {
                if(pName.equals(participant.getParticipantName())) {
                    participant.calculateValues(participant, candies);
                    newParticipantList.add(participant);
                    break;
                }
            }
        }

        // create two participant lists: top three and the other with the rest
        ParticipantList topThree = new ParticipantList();
        ParticipantList rest = new ParticipantList();
        ParticipantGroup participantGroup = new ParticipantGroup(topThree, rest);

        // create a copy of array list newParticipantList
        ArrayList<Participant> copyParticipantList = new ArrayList<>(newParticipantList);

        // find top three participants
        // GOLD participant
        System.out.println("-------------RESULT TOP THREE------------------");
        Participant goldParticipant = findTopThree(copyParticipantList, "GOLD");
        if(goldParticipant != null) {
            topThree.addToStart(goldParticipant);
            copyParticipantList.remove(goldParticipant);
        }
        // SILVER participant
        Participant silverParticipant = findTopThree(copyParticipantList, "SILVER");
        if(silverParticipant != null) {
            topThree.addToStart(silverParticipant);
            copyParticipantList.remove(silverParticipant);
        }
        // BRONZE participant
        Participant bronzeParticipant = findTopThree(copyParticipantList, "BRONZE");
        if(bronzeParticipant != null) {
            topThree.addToStart(bronzeParticipant);
            copyParticipantList.remove(bronzeParticipant);
        }
        // rest of participants
        restParticipants(rest, copyParticipantList);

        // set Participant group for each participant
        for(Participant participant : newParticipantList) {
            participant.setParticipantGroup(participantGroup);
        }
        // Test isInTheTopThree() method
        System.out.println("--------------Test isInTheTopThree() method----------------");
        Participant current = newParticipantList.get(0);
        for(int i = 1; i < newParticipantList.size(); i++) {
            Participant p = newParticipantList.get(i);
            boolean isSameGroup = current.isInTheTopThree(p);
            if(isSameGroup) {
                System.out.println("***** " + current.getParticipantName() + " is same group as " + p.getParticipantName() + " *****");
            } else {
                System.out.println("***** " + current.getParticipantName() + " is not same group as " + p.getParticipantName() + " *****");
            }
        }
        // (d) Prompt the user to enter a few participantIDs and search the list that you created for these values
        System.out.println("-------------Test search participant ID------------------");
        System.out.print("Enter participantID to search: ");
        String searchParticipantID = scanner.nextLine();
        pList2.find(searchParticipantID, true);
        // Test clone() of Participant class
        System.out.println("-------------Test clone() of Participant class-------------");
        Participant currentParticipant = new Participant("NewID1", "David", new int[]{1,2,3});
        Participant cloneParticipant = currentParticipant.clone();
        System.out.println(cloneParticipant);
        // Test insertAtIndex() method
        System.out.println("-------------Test insertAtIndex() at index 1-------------");
        pList2.insertAtIndex(cloneParticipant, 1);
        pList2.printList();
        // Test deleteFromIndex() method
        System.out.println("-------------Test deleteFromIndex() at index 1-------------");
        pList2.deleteFromIndex(1);
        pList2.printList();
        // Test deleteFromStart() method
        System.out.println("-------------Test deleteFromStart()-------------");
        pList2.deleteFromStart();
        pList2.printList();
        // Test deleteFromStart() method
        System.out.println("-------------Test replaceAtIndex() at the last index-------------");
        pList2.replaceAtIndex(cloneParticipant, pList2.getSize() - 1);
        pList2.printList();
    }

    private static void restParticipants(ParticipantList rest, ArrayList<Participant> copyParticipantList) {
        Iterator<Participant> iterator = copyParticipantList.iterator();
        while (iterator.hasNext()) {
            Participant participant = iterator.next();
            rest.addToStart(participant);
            System.out.println("Participant " + participant.getParticipantName() + " is not in top three owing to collection with low monetary value");
            iterator.remove();
        }
    }

    private static Participant findTopThree(ArrayList<Participant> newParticipantList, String ranked) {
        if(newParticipantList.size() > 0) {
            // assume the first participant having the highest monetary value
            Participant top = newParticipantList.get(0);
            boolean isSameMonetaryValue = false;
            for(int i = 1; i < newParticipantList.size(); i++) {
                if(newParticipantList.get(i).getMonetaryValue() > top.getMonetaryValue()) {
                    top = newParticipantList.get(i);
                } else if(newParticipantList.get(i).getMonetaryValue() == top.getMonetaryValue()) {
                    isSameMonetaryValue = true;
                    if(newParticipantList.get(i).getUtilityValue() > top.getUtilityValue()) {
                        top = newParticipantList.get(i);
                    }
                }
            }
            // print the outcome messages based on monetary and utility values
            if(!isSameMonetaryValue) {
                if(ranked.equals("GOLD")) {
                    System.out.println("Participant " + top.getParticipantName() + " wins " + ranked + " with collection having highest monetary value");
                } else if(ranked.equals("SILVER")) {
                    System.out.println("Participant " + top.getParticipantName() + " wins " + ranked + " with collection having second highest monetary value");
                } else {
                    System.out.println("Participant " + top.getParticipantName() + " wins " + ranked + " with collection having third highest monetary value");
                }
            } else {
                if(ranked.equals("GOLD")) {
                    System.out.println("Participant " + top.getParticipantName() + " wins " + ranked + " with collection having highest monetary and utility value");
                } else if(ranked.equals("SILVER")) {
                    System.out.println("Participant " + top.getParticipantName() + " wins " + ranked + " with collection having second highest monetary and utility value");
                } else {
                    System.out.println("Participant " + top.getParticipantName() + " wins " + ranked + " with collection having third highest monetary and utility value");
                }
            }

            return top;
        } else {
            return null;
        }
    }

    private static void checkDuplicate(ParticipantList pList, Participant p) {
        if(!pList.contains(p.getParticipantID(), false)) {
            pList.addToStart(p);
        }
    }
}