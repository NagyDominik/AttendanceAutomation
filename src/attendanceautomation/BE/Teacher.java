package attendanceautomation.BE;

import java.util.ArrayList;

/**
 * Represents a teacher.
 *
 * @author sebok
 */
public class Teacher extends Person {

    private ArrayList<ClassData> classes;
    
    public Teacher(String email, String name, int id) {
        super(id, name, email);
    }
    
    public Teacher()
    {
    }
    
    public void setClasses(ArrayList<ClassData> classes) {
        this.classes = classes;
    }

    public ArrayList<ClassData> getClasses() {
        return classes;
    }
}
