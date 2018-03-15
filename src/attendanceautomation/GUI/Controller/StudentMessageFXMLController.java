package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.StudentMessage;
import attendanceautomation.GUI.Model.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    private Label lblTeacher;
    private Model model = Model.getInstance();
    @FXML
    private JFXButton btnSend;
    
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

    @FXML
    private void btnSendClicked(ActionEvent event)
    {
        StudentMessage msg = new StudentMessage(model.getSelectedTeacher(), model.getSelectedStudent(), Calendar.getInstance(), cmbBoxStatus.getValue(), txtFieldMessage.getText());
    }
    
}
