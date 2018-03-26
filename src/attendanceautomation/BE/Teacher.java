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

    public Teacher(String email, String name, int id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClasses(ArrayList<ClassData> classes) {
        this.classes = classes;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public Teacher() {
        
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

    public int getId()
    {
        return id;
    }
}
