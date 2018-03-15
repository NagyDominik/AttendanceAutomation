/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.Teacher;
import attendanceautomation.GUI.Model.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author sebok
 */
public class StudentMessageFXMLController implements Initializable
{

    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private ComboBox<String> cmbBoxStatus;
    @FXML
    private JFXTextArea txtFieldMessage;
    @FXML
    private JFXButton btnSendClicked;
    @FXML
    private Label lblTeacher;
    private Model model = Model.getInstance();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cmbBoxStatus.getItems().setAll(new String[] {"Present", "Absent"});
        cmbBoxStatus.setValue("Present");
        
        lblTeacher.setText(model.getSelectedTeacher().getName());
    }    
    
}
