package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.StudentMessage;
import attendanceautomation.GUI.AlertWindow;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author sebok
 */
public class StudentMessageFXMLController implements Initializable {

    @FXML
    private JFXTextArea txtFieldMessage;
    @FXML
    private Label lblTeacher;
    @FXML
    private JFXButton btnSend;
    @FXML
    private Label dateLbl;

    private Model model = Model.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate date = model.getSelectedAttendanceStatus().getDateAsLocalDate();
        lblTeacher.setText(lblTeacher.getText() + model.getSelectedTeacher().getName());
        dateLbl.setText(date.format(DateTimeFormatter.ISO_DATE));
    }

    @FXML
    private void btnSendClicked(ActionEvent event)
    {
        try
        {
            boolean changeTo = !(model.getSelectedAttendanceStatus().getStatusAsBoolean());
            StudentMessage msg = new StudentMessage(model.getSelectedTeacher().getId(), model.getSelectedStudent().getId(), changeTo, txtFieldMessage.getText(), model.getSelectedAttendanceStatus().getId());
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
        catch (IOException ex) {
            Logger.getLogger(StudentMessageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
