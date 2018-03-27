package attendanceautomation.GUI.Controller;

import attendanceautomation.GUI.AlertWindow;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
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

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class OptionsWindowController implements Initializable {

    @FXML
    private ImageView profileImg;
    @FXML
    private JFXTextField currentPField;
    @FXML
    private JFXTextField newPField;
    @FXML
    private JFXTextField newPAgainField;
    
    private Model model;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
        {
            model = Model.getInstance();
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
    }

    @FXML
    private void changePasswordClicked(ActionEvent event) {
    }
    
}
