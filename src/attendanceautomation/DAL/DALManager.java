package attendanceautomation.DAL;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.BE.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
         List<ClassData> classData = new ArrayList();
        try(Connection con = cm.getConnection()) 
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Class");
            ps.executeQuery();
        } catch (Exception e) {
        }
        return classData;
    }

    /**
     * Retrieve a student based on an id.
     *
     * @return A student with the corresponding id.
     */
    public List<Student> getStudent() throws DALException 
    {
        return studentDBManager.getStudentFromDB();
    }

    /**
     *
     * Retrieve a class based on their id.
     *
     * @return The class associated with the id.
     */
    public List<Teacher> getTeacher() throws DALException 
    {
        return teacherDBManager.getTeacherFromDB();
    }

    /**
     * Attempt login with the specified email and password.
     *
     * @param email The specified email.
     * @param password The specified password.
     * @return A string representing the user type - "Teacher" for teachers,
     * "Student" for students or "None" if there is no match for the email.
     */
    public Person attemptLogin(String email, String password) throws DALException 
    {
        try (Connection con = cm.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Teacher "
                                                      + "WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Teacher();
            }
        }
        catch (Exception e) {
            throw new DALException(e);
        }
        
        try (Connection con = cm.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student "
                                                      + "WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Student();
            }
        }
        catch (Exception e) {
            throw new DALException(e);
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
    
    /**
     * Update an already existing attendance status in the database.
     * @param attendatnceStatus The attendance status that will be updated.
     * @throws DALException If something goes wrong during database operations.
     */
    public void updateAttendanceStatus(AttendanceStatus attendatnceStatus) throws DALException
    {
         teacherDBManager.updateAttendanceStatus(attendatnceStatus);
    }

    public void saveImage(Person p) throws DALException
    {
        if (p instanceof Teacher)
        {
            Teacher t = (Teacher) p;
            teacherDBManager.saveImage(t);
        }
        else if (p instanceof Student)
        {
            Student s = (Student) p;
            studentDBManager.saveImage(s);
        }
        else
        {
            throw new DALException("Not supported class! Parameter must be an instance of a Teacher or a Student!");
        }
    }
}
