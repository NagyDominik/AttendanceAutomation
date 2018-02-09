package attendanceautomation.DAL;

import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import java.time.LocalDate;

/**
 * COntains mock data (for now)
 * @author sebok
 */
public class DALManager
{
    private ConnectionManager cm;
    
    /**********************/
    //Mock data
    private ClassData mockClassData;
    private Student mockStudent;
    private Teacher mockTeacher;
    
    public DALManager()
    {
        setUpMockData();
    }
    
    private void setUpMockData()
    {
        mockClassData = new ClassData();
        mockStudent = new Student(LocalDate.now(), "student@easv.dk");
        mockTeacher = new Teacher("teacher@easv.dk");
    }
    
    /**
     * Retrieve a class based on their id.
     * @param id The id of the class.
     * @return The class associated with the id.
     */
    public ClassData getClassData(int id)
    {
        return mockClassData;
    }

     /**
     * Retrieve a class based on their id.
     * @param id The id of the class.
     * @return The class associated with the id.
     */
    public Teacher getTeacher(int id)
    {
        return mockTeacher;
    }

     /**
     * Retrieve a student based on an id.
     * @param id The id of the student.
     * @return A student with the corresponding id.
     */
    public Student getStudent(int id)
    {
        return mockStudent;
    }

     /**
     * Attempt login with the specified email and password.
     * @param email The specified email.
     * @param password The specified password.
     * @return A string representing the user type - "Teacher" for teachers, "Student" for students or "None" if there is no match for the email.
     */
    public String attemptLogin(String email, String password)
    {
        if (mockTeacher.getEmail().equals(email))
        {
            return "Teacher";
        }
        if (mockStudent.getEmail().equals(email))
        {
            return "Student";
        }
        
        return "None";
    }
}
