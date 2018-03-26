package attendanceautomation.DAL;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.BE.Teacher;
import java.util.List;

/**
 * COntains mock data (for now)
 *
 * @author sebok
 */
public class DALManager {

    private ConnectionManager cm = ConnectionManager.getInstance();
    private StudentDBManager studentDBManager = new StudentDBManager();
    private TeacherDBManager teacherDBManager = new TeacherDBManager();
    

    /**
     * *******************
     */
    //Mock data
    private List<ClassData> classData;
    private List<Student> student;
    private List<Teacher> teacher;

    public DALManager() {
        
    }
    
    /**
     * Retrieve a class based on their id.
     *
     * @return The class associated with the id.
     */
    public List<ClassData> getClassData() 
    {
        return classData;
    }

    /**
     * Retrieve a student based on an id.
     *
     * @return A student with the corresponding id.
     */
    public List<Student> getStudent() 
    {
        return student;
    }

    /**
     *
     * Retrieve a class based on their id.
     *
     * @return The class associated with the id.
     */
    public List<Teacher> getTeacher() 
    {
        return teacher;
    }

    /**
     * Attempt login with the specified email and password.
     *
     * @param email The specified email.
     * @param password The specified password.
     * @return A string representing the user type - "Teacher" for teachers,
     * "Student" for students or "None" if there is no match for the email.
     */
    public Person attemptLogin(String email, String password) 
    {
        for (Teacher loginTeacher : teacher) {
            if (loginTeacher.getEmail().equals(email) && password.equals("c596e268fea18473ea763797c0d7f4ef2cc1b13528fa7a8186c96f7da4e81cd")) {
                return loginTeacher;
            }
        }
        for (Student loginStudent : student) {
            if (loginStudent.getEmail().equals(email) && password.equals("643a5b5e16012e258750c07e363c41568b45165b4fc43874b88c21d99cb55")) {
                return loginStudent;
            }
        }
        return null;
    }
    
    /**
     * Save message to DB
     * 
     * @param msg
     * @throws DALException 
     */
    public void saveMessage(StudentMessage msg) throws DALException
    {
        studentDBManager.saveMessage(msg);
    }
    
    /**
     * Load the messages
     * @param id
     * @return The list of messages
     * @throws DALException 
     */
    public List<StudentMessage> getStudentMessages(int id) throws DALException
    {
        return teacherDBManager.getStudentMessages(id);
    }
    
    /**
     * Update an existing StudentMessage object in the database.
     * @param msg The message that will be updated.
     * @throws DALException If something goes wrong during database operations.
     */
    public void updateMessage(StudentMessage msg) throws DALException
    {
        teacherDBManager.updateMessage(msg);
    }
    
    /**
     * Checks if there are unread messages of teacher with the given id
     * @param id The id of the teacher.
     * @return True if there are  unread messages, false otherwise.
     * @throws DALException f something goes wrong during database operations. 
     */
    public boolean hasUnreadMessages(int id) throws DALException
    {
        return teacherDBManager.hasUnreadMessages(id);
    }
    
     public void updateAttendanceStatus(AttendanceStatus attendatnceStatus) throws DALException
     {
         teacherDBManager.updateAttendanceStatus(attendatnceStatus);
     }
}
