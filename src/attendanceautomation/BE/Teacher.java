package attendanceautomation.BE;

import java.util.ArrayList;

/**
 * Represents a teacher.
 * @author sebok
 */
public class Teacher
{
    private int id;
    private ArrayList<ClassData> classes;
    private String email;

    public Teacher(String email)
    {
        this.email = email;
    }
    
    public String getEmail()
    {
        return email;
    }
}
