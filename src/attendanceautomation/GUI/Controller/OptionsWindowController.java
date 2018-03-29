package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.Person;
import attendanceautomation.BE.Teacher;
import attendanceautomation.GUI.AlertWindow;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class OptionsWindowController implements Initializable {

    @FXML
    private ImageView profileImg;
    private JFXTextField currentPField;
    
    private Model model;
    @FXML
    private JFXPasswordField passFieldOld;
    @FXML
    private JFXPasswordField passFieldNew;
    @FXML
    private JFXPasswordField passFieldNewAgain;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
        {
            model = Model.getInstance();
            profileImg.setImage(model.getCurrentUser().getImage());

        } catch (ModelException ex)
        {
            AlertWindow.showAlert(ex);
        }
    }    

    @FXML
    private void selectFileClicked(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            
            File file = fileChooser.showOpenDialog(this.currentPField.getScene().getWindow());
            if (file.getAbsolutePath().endsWith("jpg") || file.getAbsolutePath().endsWith("png")) { //only alowed jpg and png
                profileImg.setImage(new Image("file:" + file.getAbsolutePath())); 
                model.getCurrentUser().setImageFile(file);
                model.saveImage(model.getCurrentUser());
            } else {
                throw new Exception("Only .jpg and .png files allowed");
            }
        }
        catch (Exception ex) {
            AlertWindow.showAlert(ex);
        }
    }

    @FXML
    private void cancelClicked(ActionEvent event) {
        Stage s = (Stage) profileImg.getScene().getWindow();
        s.close();
    }

    @FXML
    private void changePasswordClicked(ActionEvent event) {
        String old = passFieldOld.getText();
        Person currentUser = model.getCurrentUser();
        boolean isTeacher = currentUser instanceof Teacher;
        try
        {
            if (!model.authenticatePassword(currentUser.getEmail(), old, isTeacher))   //Check the old password         
            {
                AlertWindow.showAlert(new Exception("Incorrect password!"));    //If incorrect, do not let the user change it.
                return;
            }
            else
            {
                String newPass = passFieldNew.getText();
                String newPassAgain = passFieldNewAgain.getText();
                if (newPass.isEmpty() && !newPass.equals(newPassAgain))  //Check if the two entered passwords are the same, and only proceed if they are.
                {
                    AlertWindow.showAlert(new Exception("Please enter your new password twice!"));
                    return;
                }
                else
                {
                    model.changePassword(currentUser.getId(), newPass, isTeacher);
                }
            }
        } catch (ModelException ex)
        {
            AlertWindow.showAlert(ex);
        }
    }
    
}
