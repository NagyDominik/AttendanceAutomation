package attendanceautomation.BLL;

import attendanceautomation.DAL.DALManager;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;

/**
 * Serves as a pass-through layer for now.
 *
 * @author sebok
 */
public class BLLManager {

    private DALManager dManager;
    private Encrypter encrypter = new Encrypter();

    public BLLManager() {
        dManager = new DALManager();
    }

    /**
     * Retrieve a class based on an id.
     *
     * @param id The id of the class.
     * @return A class with the corresponding id.
     */
    public ClassData getClassData(int id) {
        return dManager.getClassData(id);
    }

    /**
     * Retrieve a teacher based on an id.
     *
     * @param id The id of the teacher.
     * @return A teacher with the corresponding id.
     */
    public Teacher getTeacher(int id) {
        return dManager.getTeacher(id);
    }

    /**
     * Retrieve a student based on an id.
     *
     * @param id The id of the student.
     * @return A student with the corresponding id.
     */
    public Student getStudent(int id) {
        return dManager.getStudent(id);
    }

    /**
     * Attempt login with the specified email and password.
     *
     * @param email The specified email.
     * @param password The specified password.
     * @return A string representing the user type - "Teacher" for teachers,
     * "Student" for students or "None" if there is no match for the email.
     */
    public String attemptLogin(String email, String password) {
        encrypter.encrypt(password);
        return dManager.attemptLogin(email, password);
    }
}
