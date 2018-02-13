package attendanceautomation.GUI.Model;

import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import attendanceautomation.BLL.BLLManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class Model implements Initializable {
    
    private static Model instance;
    private BLLManager bllManager = new BLLManager();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public static Model getInstance() {
        if (instance == null)
        {
            instance = new Model();
        }
        return instance;
    }

    public String authenticate(String email, String password) {
       return bllManager.attemptLogin(email, password);
    }
    
    public List<Student> getStudent() {
        return bllManager.getStudent();
    }
     public List<Teacher> getTeacher() {
        return bllManager.getTeacher();
    }
     public List<ClassData> getClassData() {
        return bllManager.getClassData();
    }

}
