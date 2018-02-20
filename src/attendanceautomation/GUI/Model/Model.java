package attendanceautomation.GUI.Model;

import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import attendanceautomation.BLL.BLLManager;
import java.net.URL;
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

    public ObservableList<Student> getStudent() {
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        studentList.addAll(bllManager.getStudent());
        return studentList;
    }

    public ObservableList<Teacher> getTeacher() {
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
        teacherList.addAll(bllManager.getTeacher());
        return teacherList;
    }

    public ObservableList<ClassData> getClassData() {
        ObservableList<ClassData> classDataList = FXCollections.observableArrayList();
        classDataList.addAll(bllManager.getClassData());
        return classDataList;
    }

    public Person getCurrentUser() {
        return currentUser;
    }

    public void setSelectedStudent(Student selected) {
        this.selectedStudent = selected;
    }

    public Student getSelectedStudent() {
        return this.selectedStudent;
    }
    
}
