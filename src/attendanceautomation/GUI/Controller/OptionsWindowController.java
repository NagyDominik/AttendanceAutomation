/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;

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
    
    private File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void selectFileClicked(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            
            URI path = fileChooser.showOpenDialog(new ContextMenu()).toURI());
            if (path.toString().endsWith("jpg") || path.toString().endsWith("png")) { //only alowed jpg and png
                file = 
               profileImg.setImage();
            } else {
                throw new Exception("Only .jpg and .png files allowed");
            }
        }
        catch (Exception ex) {
        }
    
    }

    @FXML
    private void cancelClicked(ActionEvent event) {
    }

    @FXML
    private void changePasswordClicked(ActionEvent event) {
    }
    
}
