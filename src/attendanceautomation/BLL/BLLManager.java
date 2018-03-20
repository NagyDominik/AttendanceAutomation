package attendanceautomation.BLL;

import attendanceautomation.DAL.DALManager;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.BE.Teacher;
import attendanceautomation.DAL.DALException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Serves as a pass-through layer for now.
 *
 * @author sebok
 */
public class BLLManager {

    private DALManager dManager;
    private Hasher encrypter = new Hasher();

    public BLLManager() {
        dManager = new DALManager();
    }

    /**
     * Retrieve a class based on an id.
     *
     * @return A class with the corresponding id.
     */
    public List<ClassData> getClassData() {
        return dManager.getMockClassData();
    }

    /**
     * Retrieve a teacher based on an id.
     *
     * @return A teacher with the corresponding id.
     */
    public List<Teacher> getTeacher() {
        return dManager.getMockTeacher();
    }

    /**
     * Retrieve a student based on an id.
     *
     * @return A student with the corresponding id.
     */

    public List<Student> getStudent() {
        return dManager.getMockStudent();
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
        return dManager.attemptLogin(email, encrypter.hash(password));
    }

    public void sendMessage(StudentMessage msg) throws BLLException
    {
        try
        {
            dManager.saveMessage(msg);
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
            return dManager.getStudentMessages(id);
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
            dManager.updateMessage(msg);
        } catch (DALException ex)
        {
            throw new BLLException(ex);
        }
    }
}
