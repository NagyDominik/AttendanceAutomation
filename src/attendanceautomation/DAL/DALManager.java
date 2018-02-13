package attendanceautomation.DAL;

import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import com.sun.deploy.model.LocalApplicationProperties;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * COntains mock data (for now)
 * @author sebok
 */
public class DALManager
{
    private ConnectionManager cm;
    
    /**********************/
    //Mock data
    private List<ClassData> mockClassData;
    private List<Student> mockStudent;
    private List<Teacher> mockTeacher;
    
    public DALManager()
    {
        setUpMockData();
    }
    
    private void setUpMockData()
    {
        mockClassData = new ArrayList();
        mockClassData.add(new ClassData("CS2017A"));
        mockClassData.add(new ClassData("CS2017B"));
        
        mockTeacher = new ArrayList();
        mockTeacher.add(new Teacher("teacher@easv.dk", "Jeppe Moritz"));
        mockTeacher.add(new Teacher("teacher1@easv.dk", "fasz leves"));
        
        
        mockStudent = new ArrayList();
        mockStudent.add(new Student(LocalDate.now(),"student@easv.dk", "Thomas White"));
        mockStudent.add(new Student(LocalDate.now(),"student1@easv.dk", "Jesper Boo"));
        mockStudent.add(new Student(LocalDate.now(),"student2@easv.dk", "Peter Sebok"));
        mockStudent.add(new Student(LocalDate.now(),"student3@easv.dk", "Dominik Nagy"));
        mockStudent.add(new Student(LocalDate.now(),"student4@easv.dk", "Józska Pista"));
        mockStudent.add(new Student(LocalDate.now(),"student@easv.dk", "Bence Matyasi"));
        
        mockClassData.get(0).getParticipants().addAll(mockStudent);
        mockClassData.get(1).getParticipants().addAll(mockStudent);
    }

     /**
     * Retrieve a class based on their id.
     * @param id The id of the class.
     * @return The class associated with the id.
     */
    public List<ClassData> getMockClassData() {
        return mockClassData;
    }

   /**
     * Retrieve a student based on an id.
     * @param id The id of the student.
     * @return A student with the corresponding id.
     */
    public List<Student> getMockStudent() {
        return mockStudent;
    }

    /**
     * Retrieve a class based on their id.
     * @param id The id of the class.
     * @return The class associated with the id.
     */
    public List<Teacher> getMockTeacher() {
        return mockTeacher;
    }

     /**
     * Attempt login with the specified email and password.
     * @param email The specified email.
     * @param password The specified password.
     * @return A string representing the user type - "Teacher" for teachers, "Student" for students or "None" if there is no match for the email.
     */
    public String attemptLogin(String email, String password)
    {
        for (Teacher loginTeacher : mockTeacher) {
            if (loginTeacher.getEmail().equals(email)) {
               return "Teacher";
            }
        }
        for (Student loginStudent : mockStudent) {
            if(loginStudent.getEmail().equals(email))
                return "Student";            
        }
        return "None";
    }
}
