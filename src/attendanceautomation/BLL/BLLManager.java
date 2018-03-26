package attendanceautomation.BLL;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.DAL.DALManager;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.BE.Teacher;
import attendanceautomation.DAL.DALException;
import java.util.List;

/**
 * Serves as a pass-through layer for now.
 *
 * @author sebok
 */
public class BLLManager {

    private DALManager dal;
    private Hasher encrypter = new Hasher();

    public BLLManager() {
        dal = new DALManager();
    }

    /**
     * Retrieve a class based on an id.
     *
     * @return A class with the corresponding id.
     */
    public List<ClassData> getClassData() {
        return dal.getClassData();
    }

    /**
     * Retrieve a teacher based on an id.
     *
     * @return A teacher with the corresponding id.
     */
    public List<Teacher> getTeacher() {
        return dal.getTeacher();
    }

    /**
     * Retrieve a student based on an id.
     *
     * @return A student with the corresponding id.
     */

    public List<Student> getStudent() {
        return dal.getStudent();
    }

    /**
     * Attempt login with the specified email and password.
     *
     * @param email The specified email.
     * @param password The specified password.
     * @return A string representing the user type - "Teacher" for teachers,
     * "Student" for students or "None" if there is no match for the email.
     */
    public Person attemptLogin(String email, String password) {
        return dal.attemptLogin(email, encrypter.hash(password));
    }

    public void sendMessage(StudentMessage msg) throws BLLException
    {
        try
        {
            dal.saveMessage(msg);
        } catch (DALException ex)
        {
            throw new BLLException(ex);
        }
    }

    /**
     * Load the messages
     * @return The list of messages
     */
    public List<StudentMessage> getStudentMessages(int id) throws BLLException
    {
        try
        {
            return dal.getStudentMessages(id);
        } catch (DALException ex)
        {
            throw new BLLException(ex);
        }
    }
    
    /**
     * Update an existing StudentMessage object in the database.
     * @param msg The message that will be updated.
     * @throws BLLException If something goes wrong during database operations.
     */
    public void updateMessage(StudentMessage msg) throws BLLException
    {
        try
        {
            dal.updateMessage(msg);
        } catch (DALException ex)
        {
            throw new BLLException(ex);
        }
    }

    public boolean hasUnreadMessages(int id) throws BLLException
    {
        try
        {
            return dal.hasUnreadMessages(id);
        } catch (DALException ex)
        {
            throw new BLLException(ex);
        }
    }

    /**
     * Update an existing AttendanceStatus in the database
     * @param attendatnceStatus The attendance status that will be updated
     */
    public void updateAttendanceStatus(AttendanceStatus attendatnceStatus) throws BLLException
    {
        try
        {
            dal.updateAttendanceStatus(attendatnceStatus);
        } catch (DALException ex)
        {
            throw new BLLException(ex);
        }
    }
}
