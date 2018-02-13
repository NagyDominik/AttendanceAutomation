package attendanceautomation.BE;

import java.util.ArrayList;

/**
 * Represents a class of students
 *
 * @author sebok
 */
public class ClassData {

    private int id;
    private ArrayList<Student> participants;
    private String className;

    public ClassData(String className) {
        this.className = className;
        participants = new ArrayList<>();
    }    

    public ArrayList<Student> getParticipants() {
        return participants;
    }

    public String getClassName() {
        return className;
    }
    
}

