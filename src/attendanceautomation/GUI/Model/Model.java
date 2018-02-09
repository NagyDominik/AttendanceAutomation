package attendanceautomation.GUI.Model;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class Model implements Initializable {

    private static Model instance = new Model();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public static Model getInstance() {
        return instance;
    }

    public String authenticate() {
        return "Student";
        //return "Teacher";
    }

    

}
