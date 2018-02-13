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
    private String name;

    public Teacher(String name, String email)
    {
        this.name = name;
        this.email = email;
    }
    
    public String getEmail()
    {
        return email;
    }
}
