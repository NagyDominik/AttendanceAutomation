package attendanceautomation.GUI.Model;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.BE.Teacher;
import attendanceautomation.BLL.BLLException;
import attendanceautomation.BLL.BLLManager;
import attendanceautomation.DAL.DALException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private static Model instance;
    private BLLManager bllManager = new BLLManager();
    private Person currentUser;
    private Student selectedStudent;
    private Teacher selectedTeacher;
    private AttendanceStatus selectedAttendanceStatus;
    private ObservableList<Student> students = FXCollections.observableArrayList();
    private ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
    private ObservableList<ClassData> classDataList = FXCollections.observableArrayList();

    private Model() throws ModelException {
        try {
            students.addAll(bllManager.getStudent());
            teacherList.addAll(bllManager.getTeacher());
            classDataList.addAll(bllManager.getClassData());
        }
        catch (BLLException ex) {
            throw new ModelException(ex);
        }
    }

    public static Model getInstance() throws ModelException {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    /**
     * Return a imageFile (Teacher or Student) based on the email and password.
     *
     * @param email The login email.
     * @param password The login password.
     * @return The user with the given email and password.
     */
    public Person authenticate(String email, String password) throws ModelException {
        try {
            Person user = bllManager.attemptLogin(email, password);
            if (user instanceof Teacher) {
                currentUser = (Teacher) user;
            }
            if (user instanceof Student) {
                currentUser = (Student) user;
            }
            return user;
        }
        catch (BLLException ex) {
            throw new ModelException(ex);
        }
    }

    /**
     * Retrieve a list of students.
     *
     * @return A list of students.
     */
    public ObservableList<Student> getStudents() {
        return students;
    }

    /**
     * Retrieve a list of Teachers.
     *
     * @return A list of Teachers.
     */
    public ObservableList<Teacher> getTeacher() {
        return teacherList;
    }

    /**
     * Retrieve a list of ClassData.
     *
     * @return A list of ClassData objects.
     */
    public ObservableList<ClassData> getClassData() {
        return classDataList;
    }

    /**
     * Return the current user (Teacher or Student).
     *
     * @return The current user.
     */
    public Person getCurrentUser() {
        return currentUser;
    }

    /**
     * Set the selected student to the given object.
     *
     * @param selected The selected student.
     */
    public void setSelectedStudent(Student selected) {
        this.selectedStudent = selected;
    }

    /**
     * Return the selected student.
     *
     * @return The selected student.
     */
    public Student getSelectedStudent() {
        return this.selectedStudent;
    }

    /**
     * Set the selected Teacher to the given object.
     *
     * @param selectedTeacher The selected teacher.
     */
    public void setSelectedTeacher(Teacher selectedTeacher) {
        this.selectedTeacher = selectedTeacher;
    }

    /**
     * Return the selected teacher;
     *
     * @return The selected teacher.
     */
    public Teacher getSelectedTeacher() {
        return selectedTeacher;
    }

    /**
     * Set the selected AttendanceInfo to the given object.
     *
     * @param stat The selected AttendanceInfo.
     */
    public void setSelectedAttendanceInfo(AttendanceStatus stat) {
        this.selectedAttendanceStatus = stat;
    }

    /**
     * Return the selected AttendanceStatus.
     *
     * @return The selected AttendanceStatus.
     */
    public AttendanceStatus getSelectedAttendanceStatus() {
        return selectedAttendanceStatus;
    }

    /**
     * Save the given message to the database.
     *
     * @param msg The message that will be saved.
     * @throws ModelException If an error occurs during saving.
     */
    public void sendMessage(StudentMessage msg) throws ModelException {
        try {
            bllManager.sendMessage(msg);
        }
        catch (BLLException ex) {
            throw new ModelException(ex);
        }
    }

    /**
     * Return a list of messages for a teacher with the given id.
     *
     * @param id The id of a teacher whos messages will be retrieved.
     * @return The list of messages for the teacher with the given id.
     * @throws ModelException If an error occurs during database operations.
     */
    public List<StudentMessage> getMessages(int id) throws ModelException {
        try {
            return bllManager.getStudentMessages(id);
        }
        catch (BLLException ex) {
            throw new ModelException(ex);
        }
    }

    /**
     * Filter the history of a student based on an optional start and end date.
     *
     * @param start (Optional) Start date.
     * @param end (Optional) End date.
     * @return List of AttendanceStatus object where the date falls between the
     * start and end dates.
     */
    public ObservableList<AttendanceStatus> filterStudentHistory(LocalDate start, LocalDate end) {
        ObservableList<AttendanceStatus> filtered = FXCollections.observableArrayList();
        for (AttendanceStatus attendanceStatus : selectedStudent.getAttendanceInfo()) {
            if (attendanceStatus.getDateAsLocalDate().isAfter(start) && attendanceStatus.getDateAsLocalDate().isBefore(end)) {
                filtered.add(attendanceStatus);
            }
        }
        return filtered;
    }

    /**
     * Retrieve an AttendanceStatus object based on the id of a student and the
     * id of the AttendanceStudent object.
     *
     * @param studentId
     * @param attendanceHistoryId The id of the attendance status.
     * @return An attendanceStatuus object with the given id.
     */
    public AttendanceStatus getAttendanceStatusBasedOnId(int studentId, int attendanceHistoryId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                for (AttendanceStatus attendanceStatus : student.getAttendanceInfo()) {
                    if (attendanceStatus.getId() == attendanceHistoryId) {
                        return attendanceStatus;
                    }
                }
                break;
            }
        }
        return null;
    }

    /**
     * Retrieve a Student based on its id.
     *
     * @param studentId The id of the Student.
     * @return The Student with the given id.
     */
    public Student getStudents(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    /**
     * Checks if the current user has unread messages.
     *
     * @return True if the current user has unread messages, false otherwise.
     */
    public boolean hasUnreadMessage(int id) throws ModelException{
        try
        {
            return bllManager.hasUnreadMessages(id);
        } catch (BLLException ex)
        {
            throw new ModelException(ex);
        }
    }
    
    /**
     * Update an existing StudentMessage object in the database.
     * @param msg The message that will be updated.
     * @throws ModelException If something goes wrong during database operations.
     */
    public void updateMessage(StudentMessage msg) throws ModelException
    {
        try
        {
            bllManager.updateMessage(msg);
        } catch (BLLException ex)
        {
            throw new ModelException(ex);
        }
    }

    /**
     * Update an existing AttendanceStatus in the database
     * @param attendatnceStatus The attendance status that will be updated
     */
    public void updateAttendanceStatus(AttendanceStatus attendatnceStatus) throws ModelException
    {
        try
        {
            bllManager.updateAttendanceStatus(attendatnceStatus);
        } catch (BLLException ex)
        {
            throw new ModelException(ex);
        }
    }
    
    /**
     * Save an image of a imageFile (Teacher or Student) to the database.
     * @param p The person whose image will be saved.
     */
    public void saveImage(Person p) throws ModelException
    {
        try
        {
            bllManager.saveImage(p);
        } catch (BLLException ex)
        {
            throw new ModelException(ex);
        }
    }
    
    public void saveLocalData(String email, String password) throws ModelException {
        try {
            bllManager.saveLocalData(email, password);
        }
        catch (BLLException ex) {
            throw new ModelException(ex);
        }
    }
}
