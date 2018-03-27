package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXCheckBox rememberCBox;

    private Model model;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.model = Model.getInstance();
            addListenersAndHandlers();
        }
        catch (ModelException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loginClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            if (model.authenticate(emailField.getText(), passwordField.getText()) instanceof Teacher) {
                loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/TeacherWindow.fxml"));
            } else if (model.authenticate(emailField.getText(), passwordField.getText()) instanceof Student) {
                loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/StudentWindow.fxml"));
            } else {
                newAlert(new Exception("Invalid email or password"));
            }
            if (rememberCBox.isSelected()) {
                model.saveLocalData(emailField.getText(), passwordField.getText());
            }
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (ModelException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            newAlert(ex);
        }
    }

    @FXML
    private void helpClicked(ActionEvent event) {
        Alert help = new Alert(Alert.AlertType.CONFIRMATION, "Email - Your school email address, provided by the school.\n"
                + "Password - The password you gave the first time you logged in.\n\n"
                + "If either of them is missing or you forgot it, please contact\nthe IT Support.", ButtonType.OK);
        help.setHeaderText("Login help");
        Stage stage = (Stage) help.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/img/help.png"));
        stage.setTitle("Help");
        help.show();
    }

    private void newAlert(Exception ex) {
        Alert a = new Alert(Alert.AlertType.ERROR, "Error: " + ex.getMessage(), ButtonType.OK);
        a.show();
    }

    private void addListenersAndHandlers() {
        passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    loginClicked(new ActionEvent());
                }
            }
        });
    }

}
