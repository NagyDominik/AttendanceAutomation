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

    private DALManager dalManager;
    private Hasher hasher = new Hasher();

    public BLLManager() {
        dalManager = new DALManager();
    }

    /**
     * Retrieve a class based on an id.
     *
     * @return A class with the corresponding id.
     */
    public List<ClassData> getClassData() {
        return dalManager.getClassData();
    }

    /**
     * Retrieve a teacher based on an id.
     *
     * @return A teacher with the corresponding id.
     */
    public List<Teacher> getTeacher() throws BLLException {
        try {
            return dalManager.getTeacher();
        }
        catch (DALException ex) {
            throw new BLLException(ex);
        }
    }

    /**
     * Retrieve a student based on an id.
     *
     * @return A student with the corresponding id.
     */
    public List<Student> getStudent() throws BLLException {
        try {
            return dalManager.getStudent();
        }
        catch (DALException ex) {
            throw new BLLException(ex);
        }
    }

    /**
     * Attempt login with the specified email and password.
     *
     * @param email The specified email.
     * @param password The specified password.
     * @return A string representing the user type - "Teacher" for teachers,
     * "Student" for students or "None" if there is no match for the email.
     */
    public Person attemptLogin(String email, String password) throws BLLException {
        try {
            return dalManager.attemptLogin(email, hasher.hash(password));
        }
        catch (DALException ex) {
            throw new BLLException(ex);
        }
    }

    public void sendMessage(StudentMessage msg) throws BLLException {
        try {
            dalManager.saveMessage(msg);
        }
        catch (DALException ex) {
            throw new BLLException(ex);
        }
    }

    /**
     * Load the messages
     *
     * @return The list of messages
     */
    public List<StudentMessage> getStudentMessages(int id) throws BLLException {
        try {
            return dalManager.getStudentMessages(id);
        }
        catch (DALException ex) {
            throw new BLLException(ex);
        }
    }

    /**
     * Update an existing StudentMessage object in the database.
     *
     * @param msg The message that will be updated.
     * @throws BLLException If something goes wrong during database operations.
     */
    public void updateMessage(StudentMessage msg) throws BLLException {
        try {
            dalManager.updateMessage(msg);
        }
        catch (DALException ex) {
            throw new BLLException(ex);
        }
    }

    public boolean hasUnreadMessages(int id) throws BLLException {
        try {
            return dalManager.hasUnreadMessages(id);
        }
        catch (DALException ex) {
            throw new BLLException(ex);
        }
    }

    /**
     * Update an existing AttendanceStatus in the database
     *
     * @param attendatnceStatus The attendance status that will be updated
     */
    public void updateAttendanceStatus(AttendanceStatus attendatnceStatus) throws BLLException {
        try {
            dalManager.updateAttendanceStatus(attendatnceStatus);
        }
        catch (DALException ex) {
            throw new BLLException(ex);
        }
    }

    /**
<<<<<<< HEAD
     * Save an image of a imageFile (Teacher or Student) to the database.
=======
     * Save an image of a Person (Teacher or Student) to the database.
     *
>>>>>>> cbf64a4d58dd4c42b8726a395009525677f1ff98
     * @param p The person whose image will be saved.
     */
    public void saveImage(Person p) throws BLLException {
        try {
            dalManager.saveImage(p);
        }
        catch (DALException ex) {
            throw new BLLException(ex);
        }
    }

    public void saveLocalData(String email, String password) throws BLLException {
        try {
            dalManager.saveLocalData(email, hasher.hash(password));
        }
        catch (DALException ex) {
            throw new BLLException(ex);
        }
    }
}
