package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BusTest {
    private Bus bus;
    private Bus bus1;
    private Set<Student> students = new HashSet<>();
    private Set<Student> students1 = new HashSet<>();
    private Student student1;
    private Student student2;
    private Chaperone chaperone;

    @BeforeEach
    void runBefore() {
        bus = new Bus(10, 2);
        bus1  = new Bus(10,5);
        student1 = new Student(1, "dd", 3);
        student2 = new Student(2, "ss", 2);
    }


    // test constructor
    @Test
    void testConstructor() {
        assertEquals(10, bus.getId());
        assertEquals(2, bus.getCapacity());
        assertEquals(null, bus.getChaperone());
        assertFalse(bus1.hasChaperone());
    }

        @Test
        void testSetAndSetChaperone(){
            assertEquals(null, bus.getChaperone());
            assertFalse(bus1.hasChaperone());
            Chaperone chaperone1 = new Chaperone("a");
            bus1.setChaperone(chaperone1);
            assertTrue(bus1.hasChaperone());
            assertEquals(chaperone1,bus1.getChaperone());

//        assertNull(bus.getChaperone());
//        assertFalse(bus.hasChaperone());
//        assertFalse(bus.isFull());
    }

    @Test
    void testNoStudents() {
        chaperone = new Chaperone("a");
        assertEquals(students, bus.getStudents());

        bus.setChaperone(chaperone);
        assertTrue(bus.hasChaperone());
        assertEquals(chaperone, bus.getChaperone());
        assertFalse(bus.isFull());
        assertEquals(0, bus.getStudents().size());
    }

    @Test
    void testOneStudent() {
        bus.addStudent(student1);
        students.add(student1);

        assertEquals(students, bus.getStudents());
        assertEquals(bus, student1.getAssignedBus());

        bus1 = new Bus(1, 20);
        bus1.addStudent(student1);
        students1.add(student1);
        assertEquals(students1, bus1.getStudents());
        assertEquals(1, bus1.getStudents().size());
        assertEquals(bus1, student1.getAssignedBus());

        bus.removeStudent(student1);
        assertEquals(0, bus.getStudents().size());
    }

    @Test
    void testTwoStudent() {
        bus.addStudent(student1);
        students.add(student1);
        assertEquals(students, bus.getStudents());

        bus.addStudent(student2);
        students.add(student2);
        assertEquals(students, bus.getStudents());

        assertTrue(bus.isFull());

        bus1 = new Bus(3, 3);
        students1.add(student1);
        bus1.addStudent(student1);
        students.remove(student1);

        assertEquals(students1, bus1.getStudents());
        assertEquals(students, bus.getStudents());

        bus.addStudent(student2);
        assertEquals(students, bus.getStudents());

        bus.removeStudent(student2);
        assertEquals(0, bus.getStudents().size());
    }

    @Test
    void testStudentAssignedToOtherBus() {
        bus.addStudent(student1);
        bus1 = new Bus(3, 3);
        students.add(student1);

        assertEquals(0, bus1.getStudents().size());
        assertEquals(students, bus.getStudents());

        bus1.addStudent(student1);
        students1.add(student1);
        students.remove(student1);

        assertEquals(1, bus1.getStudents().size());
        assertEquals(students1, bus1.getStudents());
        assertEquals(0, bus.getStudents().size());
        assertEquals(students, bus.getStudents());

        bus.addStudent(student1);
        students1.remove(student1);
        students.add(student1);
        assertEquals(1, bus.getStudents().size());
        assertEquals(students, bus.getStudents());
        assertEquals(0, bus1.getStudents().size());
        assertEquals(students1, bus1.getStudents());

        bus.addStudent(student2);
        students.add(student2);
        assertTrue(bus.isFull());
        assertEquals(2, bus.getStudents().size());
        assertEquals(students, bus.getStudents());

        bus1.addStudent(student2);
        students.remove(student2);
        students1.add(student2);
        assertEquals(1, bus.getStudents().size());
        assertEquals(students, bus.getStudents());
        assertEquals(1, bus1.getStudents().size());
        assertEquals(students1, bus1.getStudents());

        assertFalse(bus.isFull());
        assertFalse(bus1.isFull());
    }

    @Test
    void addStudentToPreviousBus (){
        // set up: add student to a bus;
        assertEquals(0,bus.getStudents().size());
        assertFalse(bus.getStudents().contains(student1));
        bus.addStudent(student1);
        assertTrue(bus.getStudents().contains(student1));
        assertEquals(1,bus.getStudents().size());

        // call to the method we want to test;
        assertEquals(bus,student1.getAssignedBus());
        assertFalse(bus1.getStudents().contains(student1));
        bus1.addStudent(student1);

        // 1) bus does not contain student1
        assertFalse(bus.getStudents().contains(student1));
        // 2) student1 is in bus 1
        assertEquals(bus1,student1.getAssignedBus());
        // 3) bus1 contains student1
        assertTrue(bus1.getStudents().contains(student1));
    }

    @Test
    void testStudentNotAddToPreviousBus( ){
        assertFalse(bus1.getStudents().contains(student1));
        bus1.addStudent(student1);
        assertEquals(bus1,student1.getAssignedBus());
        assertTrue(bus1.getStudents().contains(student1));
    }

    @Test
    void testRemoveStudentInBus() {
        // set up: add student to bus
        assertFalse(bus1.getStudents().contains(student1));
        bus1.addStudent(student1);
        assertEquals(bus1,student1.getAssignedBus());
        assertTrue(bus1.getStudents().contains(student1));

        // remove student
        bus1.removeStudent(student1);
        assertFalse(bus1.getStudents().contains(student1));
        assertEquals(null, student1.getAssignedBus());
        assertFalse(student1.isAssignedToBus());
    }

    @Test
    void testRemoveStudentNotInBus() {
        assertFalse(bus1.getStudents().contains(student1));
        assertFalse(student1.isAssignedToBus());
        assertEquals(null, student1.getAssignedBus());

        bus1.removeStudent(student1);
        assertFalse(bus1.getStudents().contains(student1));
        assertFalse(student1.isAssignedToBus());
        assertEquals(null, student1.getAssignedBus());

    }

}