package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.GUI.Model.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
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
    private Label teacherLbl;
    @FXML
    private Label dateLbl;

    private Model model = Model.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate date = model.getSelectedAttendanceStatus().getDateAsLocalDate();
        dateLbl.setText(dateLbl.getText() + date);
        lblTeacher.setText(model.getSelectedTeacher().getName());
    }

    @FXML
    private void btnSendClicked(ActionEvent event) {
        StudentMessage msg = new StudentMessage(model.getSelectedTeacher(), (Student) model.getCurrentUser(), Calendar.getInstance(), model.getSelectedAttendanceStatus().getDate(), txtFieldMessage.getText());
    }

    @FXML
    private void btnCancelClicked(ActionEvent event) {
        Stage stage = (Stage) btnSend.getScene().getWindow();
        stage.close();
        /*try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/StudentWindow.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) btnSend.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ex) {
            Logger.getLogger(StudentMessageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}
