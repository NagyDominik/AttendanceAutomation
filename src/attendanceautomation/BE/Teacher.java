package attendanceautomation.BE;

import java.util.ArrayList;

/**
 * Represents a teacher.
 *
 * @author sebok
 */
public class Teacher extends Person {

    private int id;
    private ArrayList<ClassData> classes;
    private String email;
    private String name;

    public Teacher(String email, String name) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<ClassData> getClasses() {
        return classes;
    }

    public String getName() {
        return name;
    }

}
