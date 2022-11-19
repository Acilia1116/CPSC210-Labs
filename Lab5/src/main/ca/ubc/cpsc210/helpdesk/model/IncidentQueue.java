package ca.ubc.cpsc210.helpdesk.model;

import java.util.LinkedList;

// Represents a queue of incidents to be handled by helpdesk
// with maximum size MAX_SIZE
public class IncidentQueue {
    public static final int MAX_SIZE = 10;
    private LinkedList<Incident> incidents;
    // TODO: complete the design of the IncidentQueue class


    // EFFECTS: initialize a new incident queue, and queue is empty.
    public IncidentQueue() {
        incidents = new LinkedList<>();
    }

    // REQUIRE: the incident with the same caseNumber are not already in queue.
    // MODIFIES: this
    // EFFECTS: if the queue is not full, adds it to the end of the queue and returns true.
    // If the queue is full, the method returns false.
    public boolean addIncident(Incident incident) {
        if (!isFull()) {
            incidents.add(incident);
            return true;
        }
        return false;
    }

    // REQUIRES: the queue is not empty
    // MODIFIES: this
    // EFFECTS:  removes the Incident at the front of the queue and returns it.
    public Incident getNextIncident() {
        return incidents.removeFirst();
    }

    // EFFECTS: return the position of the incident in the queue with the given case number;
    // return -1 is there if no incident in the queue with the given case number
    public int getPositionInQueueOfCaseNumber(int caseNum) {
        for (int index = 0; index < length(); index++) {
            if (incidents.get(index).getCaseNum() == caseNum) {
                return index + 1;
            }
        }
        return -1;
    }

    //EFFECT: return the number of incident currently in the queue
    public int length() {
        return incidents.size();
    }

    //EFFECT: true if the queue is empty, false otherwise
    public boolean isEmpty() {
        return incidents.size() == 0;
    }

    //EFFECT: true if the queue is full, false otherwise
    public boolean isFull() {
        return incidents.size() == IncidentQueue.MAX_SIZE;
    }

}
