package attendanceautomation.GUI.Controller;

import attendanceautomation.GUI.Model.Model;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXTextField emailField;
    private Model model;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.model = Model.getInstance();
    }

    @FXML
    private void loginClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            if (model.authenticate(emailField.getText(), passwordField.getText()).equals("Teacher")) {
                loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/TeacherWindow.fxml"));
            }
            else if (model.authenticate(emailField.getText(), passwordField.getText()).equals("Student")) {
                loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/StudentWindow.fxml"));
            } else {
                newAlert(new Exception("Invalid email or password"));
                return;
            }
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            newAlert(ex);
        }
    }

    @FXML
    private void helpClicked(ActionEvent event) {

    }

    private void newAlert(Exception ex) {
        Alert a = new Alert(Alert.AlertType.ERROR, "Error: " + ex.getMessage(), ButtonType.OK);
        a.show();
    }
}
