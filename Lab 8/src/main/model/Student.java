package model;

// Represents a student with an id, name, the grade in which the student is registered and bus to which
// student is assigned to travel
public class Student {

    private int id;
    private int grade;
    private String name;
    private Bus bus;

    // EFFECTS: constructs student with id, name and registered grade, and with no bus assigned
    public Student(int id, String name, int grade) {
        this.id = id;
        this.grade = grade;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public Bus getAssignedBus() {
        return bus;
    }

    // EFFECTS: returns true if student is assigned to a bus, false otherwise
    public boolean isAssignedToBus() {
        return getAssignedBus() != null;
    }

    // REQUIRES: !bus.isFull()
    // MODIFIES: this, bus
    // EFFECTS: assigns student to travel on bus
    public void assignToBus(Bus bus) {
//        if (!(getAssignedBus() == bus) && isAssignedToBus()) {
//            getAssignedBus().removeStudent(this);
//            // stub
//        }
//        this.bus = bus;
//        bus.addStudent(this);

        if (bus != this.bus) {
            if (this.bus != null) {
                this.bus.removeStudent(this);
            }
            this.bus = bus;
            if (this.bus != null) {
                this.bus.addStudent(this);

            }
        }
    }

    // MODIFIES: this, Bus b = getAssignedBus()
    // EFFECTS: if student is assigned to a bus, removes student from assigned bus;
    // otherwise has no effect
    public void removeFromBus() {
//        if (isAssignedToBus()) {
//            bus.removeStudent(this);
//            bus = null;
//        }

        if (isAssignedToBus()) {
            this.bus.removeStudent(this);
            this.bus = null;
        }
    }
}
