package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.StudentMessage;
import attendanceautomation.GUI.AlertWindow;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
        
        LocalDate date = model.getSelectedAttendanceStatus().getDateAsLocalDate();
        
        datePicker.setValue(date);
        lblTeacher.setText(model.getSelectedTeacher().getName());
    }    

    @FXML
    private void btnSendClicked(ActionEvent event)
    {
        try
        {
            StudentMessage msg = new StudentMessage(model.getSelectedTeacher(), model.getSelectedStudent(), datePicker.getValue(), cmbBoxStatus.getValue(), txtFieldMessage.getText(), model.getSelectedAttendanceStatus());
            model.sendMessage(msg);
            close();
        } catch (ModelException ex)
        {
            Logger.getLogger(StudentMessageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            AlertWindow.showAlert(ex);
        }
    }

    @FXML
    private void btnCancelClicked(ActionEvent event)
    {  
        close();
    }

    private void close()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/StudentWindow.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) btnSend.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(StudentMessageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
