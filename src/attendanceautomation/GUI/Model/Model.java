package attendanceautomation.GUI.Model;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.BE.Teacher;
import attendanceautomation.BLL.BLLException;
import attendanceautomation.BLL.BLLManager;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Model implements Initializable {

    private static Model instance;
    private BLLManager bllManager = new BLLManager();
    private Person currentUser;
    private Student selectedStudent;
    private Teacher selectedTeacher;
    private AttendanceStatus selectedAttendanceStatus;
    private List<Student> students = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    
    
    public static void newAlert(Exception ex) {
        Alert a = new Alert(Alert.AlertType.ERROR, "Error: " + ex.getMessage(), ButtonType.OK);
        a.show();
    }

    /**
     * Return a Person (Teacher or Student) based on the email and password.
     * @param email The login email.
     * @param password The login password.
     * @return The user with the given email and password.
     */
    public Person authenticate(String email, String password) {
        Person user = bllManager.attemptLogin(email, password);
        if (user instanceof Teacher) {
            currentUser = (Teacher) user;
        }
        if (user instanceof Student) {
            currentUser = (Student) user;
        }
        return user;
    }

    /**
     * Retrieve a list of students.
     * @return A list of students.
     */
    public ObservableList<Student> getStudents() {
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        studentList.addAll(bllManager.getStudent());
        students.addAll(studentList);
        return studentList;
    }

    /**
     * Retrieve a list of Teachers.
     * @return A list of Teachers.
     */
    public ObservableList<Teacher> getTeacher() {
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
        teacherList.addAll(bllManager.getTeacher());
        return teacherList;
    }

    /**
     * Retrieve a list of ClassData.
     * @return  A list of ClassData objects.
     */
    public ObservableList<ClassData> getClassData() {
        ObservableList<ClassData> classDataList = FXCollections.observableArrayList();
        classDataList.addAll(bllManager.getClassData());
        return classDataList;
    }

    /**
     * Return the current user (Teacher or Student).
     * @return The current user.
     */
    public Person getCurrentUser() {
        return currentUser;
    }

    /**
     * Set the selected student to the given object.
     * @param selected The selected student.
     */
    public void setSelectedStudent(Student selected) {
        this.selectedStudent = selected;
    }

    /**
     * Return the selected student.
     * @return The selected student.
     */
    public Student getSelectedStudent() {
        return this.selectedStudent;
    }

    /**
     * Set the selected Teacher to the given object.
     * @param selectedTeacher The selected teacher.
     */
    public void setSelectedTeacher(Teacher selectedTeacher)
    {
        this.selectedTeacher = selectedTeacher;
    }

    /**
     * Return the selected teacher;
     * @return The selected teacher.
     */
    public Teacher getSelectedTeacher()
    {
        return selectedTeacher;
    }

    /**
     * Set the selected AttendanceInfo to the given object.
     * @param stat The selected AttendanceInfo.
     */
    public void setSelectedAttendanceInfo(AttendanceStatus stat)
    {
        this.selectedAttendanceStatus = stat;
    }

    /**
     * Return the selected AttendanceStatus.
     * @return The selected AttendanceStatus.
     */
    public AttendanceStatus getSelectedAttendanceStatus()
    {
        return selectedAttendanceStatus;
    }

    /**
     * Save the given message to the database.
     * @param msg The message that will be saved.
     * @throws ModelException If an error occurs during saving. 
     */
    public void sendMessage(StudentMessage msg) throws ModelException
    {
        try
        {
            bllManager.sendMessage(msg);
        } catch (BLLException ex)
        {
            throw new ModelException(ex);
        }
    }

    /**
     * Return a list of messages for a teacher with the given id.
     * @param id The id of a teacher whos messages will be retrieved.
     * @return The list of messages for the teacher with the given id.
     * @throws ModelException If an error occurs during database operations.
     */
    public List<StudentMessage> getMessages(int id) throws ModelException
    {
        try
        {
            return bllManager.getStudentMessages(id);
        } catch (BLLException ex)
        {
            throw new ModelException(ex);
        }
    }
    
    /**
     * Filter the history of a student based on an optional start and end date.
     * @param start (Optional) Start date.
     * @param end (Optional) End date.
     * @return List of AttendanceStatus object where the date falls between the start and end dates.
     */
    public ObservableList<AttendanceStatus> filterStudentHistory(LocalDate start, LocalDate end) {
        ObservableList<AttendanceStatus> filtered = FXCollections.observableArrayList();
        if (start == null) {
            start = LocalDate.MIN;
        }
        if (end == null) {
            end = LocalDate.MAX;
        }
        for (AttendanceStatus attendanceStatus : selectedStudent.getAttendanceInfo()) {
            if (attendanceStatus.getDateAsLocalDate().isAfter(start) && attendanceStatus.getDateAsLocalDate().isBefore(end.plusDays(1))) {
                filtered.add(attendanceStatus);
            }
        }
        return filtered;
    }
    
    /**
     * Retrieve an AttendanceStatus object based on the id of a student and the id of the AttendanceStudent object.
     * @param studentId
     * @param attendanceHistoryId The id of the attendance status.
     * @return An attendanceStatuus object with the given id. 
     */
    public AttendanceStatus getAttendanceStatusBasedOnId(int studentId, int attendanceHistoryId)
    {
        for (Student student : students)
        {
            if (student.getId() == studentId)
            {
                for (AttendanceStatus attendanceStatus : student.getAttendanceInfo())
                {
                    if (attendanceStatus.getId() == attendanceHistoryId)
                    {
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
     * @param studentId The id of the Student.
     * @return The Student with the given id.
     */
    public Student getStudents(int studentId)
    {
        for (Student student : students)
        {
            if (student.getId() == studentId)
            {
                return student;
            }
        }
        return null;
    }
    
}
