package attendanceautomation.GUI.Model;

import attendanceautomation.BLL.BLLManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class Model implements Initializable {

    private static Model instance;
    private BLLManager bllManager = new BLLManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public String authenticate(String email, String password) {
        return bllManager.attemptLogin(email, password);
    }

}
