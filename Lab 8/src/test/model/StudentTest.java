package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Bus bus;
    private Set<Student> students = new HashSet<>();
    private Student student1;
    private Student student2;

    @BeforeEach
    void runBefore() {
        student1 = new Student(1, "dd", 3);
        bus = new Bus(10, 2);
    }

    @Test
    void testConstructor() {
        assertEquals(1, student1.getId());
        assertEquals("dd", student1.getName());
        assertEquals(3, student1.getGrade());
        assertFalse(student1.isAssignedToBus());
        assertNull(student1.getAssignedBus());

        student1.removeFromBus();
        assertFalse(student1.isAssignedToBus());
        assertNull(student1.getAssignedBus());
    }

    @Test
    void testAssignToBus() {
        student1.assignToBus(bus);
        assertTrue(student1.isAssignedToBus());
        assertEquals(bus, student1.getAssignedBus());
        assertEquals(1, bus.getStudents().size());

        Bus bus1 = new Bus(3, 3);
        student1.assignToBus(bus1);
        assertEquals(bus1, student1.getAssignedBus());

        student1.removeFromBus();
        assertFalse(student1.isAssignedToBus());
        assertNull(student1.getAssignedBus());
    }


}