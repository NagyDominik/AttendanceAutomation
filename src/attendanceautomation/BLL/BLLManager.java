package attendanceautomation.BLL;

import attendanceautomation.DAL.DALManager;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import java.util.List;

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
}
