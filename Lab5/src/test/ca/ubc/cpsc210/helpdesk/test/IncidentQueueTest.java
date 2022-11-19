package ca.ubc.cpsc210.helpdesk.test;

import ca.ubc.cpsc210.helpdesk.model.IncidentQueue;
import ca.ubc.cpsc210.helpdesk.model.Incident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class IncidentQueueTest {
    // Design your unit tests for the IncidentQueue class here

    private IncidentQueue queue;
    private Incident incident1;
    private Incident incident2;

    @BeforeEach
    public void SetUp() {
        queue = new IncidentQueue();
        incident1 = new Incident(5, "bipolar");
        incident2 = new Incident(12,"back pain");
    }

    @Test
    public void testConstructor() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testAddIncidentOnlyOne() {
        // add one incident to the empty incident queue
        // test the IncidentQueue is empty
        assertTrue(queue.isEmpty());
        // add a new incident in the queue
        assertTrue(queue.addIncident(incident1));
        // verify length
        assertEquals(1, queue.length());
        assertFalse(queue.isFull());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testAddIncidentTwice() {
        // add one incident to the empty incident queue
        // test the IncidentQueue is empty
        assertTrue(queue.isEmpty());
        // add a new incident in the queue
        queue.addIncident(incident1);
        // verify length, not empty
        assertEquals(1, queue.length());
        assertFalse(queue.isFull());
        assertFalse(queue.isEmpty());

        // try to add another incident with the exactly same case number, but fail
        assertTrue(queue.addIncident(incident2));
        assertEquals(2, queue.length());
        assertFalse(queue.isEmpty());

    }

    @Test
    public void testAddIncidentFull() {
        //IncidentQueue is empty
        assertTrue(queue.isEmpty());
        // create 10 incidents with unique case number, and add them into incident queue
        for( int i = 1; i <= IncidentQueue.MAX_SIZE; i++){
            Incident incident = new Incident(i, "i");
            queue.addIncident(incident);
            assertEquals(i,queue.length());
        }
        // verify the queue is full
        assertTrue(queue.isFull());

        // add another incident into queue, but fail
        assertFalse(queue.addIncident(incident2));

        // verify the queue length is unchanged
        assertEquals(IncidentQueue.MAX_SIZE,queue.length());
    }

    @Test
    public void testGetIncident(){
        // add one incident to the empty incident queue
        queue.addIncident(incident1);
        // add another incident with different caseNum to incident queue
        queue.addIncident(incident2);
        // verify length
        assertEquals(2,queue.length());
        // remove the first incident of the queue
        queue.getNextIncident();
        // verify the queue length
        assertEquals(1,queue.length());
    }

    @Test
    public void testGetPositionInQueue(){
        // add incidents to the empty incident queue
        queue.addIncident(incident1);
        queue.addIncident(incident2);
        // verify length
        assertEquals(2,queue.length());
        // get position in queue with given case number
        assertEquals(1,queue.getPositionInQueueOfCaseNumber(5));
        assertEquals(2,queue.getPositionInQueueOfCaseNumber(12));

    }

    @Test
    public void testGetPositionInQueueFail(){
        // add incidents to the empty incident queue
        queue.addIncident(incident1);
        queue.addIncident(incident2);
        // verify length
        assertEquals(2,queue.length());
        // get position in queue with given case number, but fail
        assertEquals(-1,queue.getPositionInQueueOfCaseNumber(1));
    }

}