package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.GUI.AlertWindow;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sebok
 */
public class TeacherMessageController implements Initializable
{

    @FXML
    private Label lblFrom;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblRequest;
    @FXML
    private JFXTextArea txtAreaMessage;
    @FXML
    private TableView<StudentMessage> tableViewMessages;
    @FXML
    private TableColumn<StudentMessage, String> tableColumnsMessages;
    ObservableList<StudentMessage> messages = FXCollections.observableArrayList();
    Model model = Model.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        setUpTableColumn();
//        setUpListeners();
//        try
//        {
//            messages.addAll(model.getMessages(model.getCurrentUser().getId()));
//            tableViewMessages.setItems(messages);
//        } catch (ModelException ex)
//        {
//            AlertWindow.showAlert(ex);
//        }
//        System.out.println("");
    }    

       @FXML
    private void btnCancelClicked(ActionEvent event)
    {  
        close();
    }

    /**
     * Load the previous window and close this one.
     */
    private void close()
    {

        Stage stage = (Stage) txtAreaMessage.getScene().getWindow();
        stage.close();
    }

    private void setUpTableColumn()
    {
        tableColumnsMessages.setCellValueFactory(new PropertyValueFactory("id"));
    }

    /**
     * Set up a listener and a method to automatically change the labels to reflect the contents of a newly selected message.
     */
    private void setUpListeners()
    {
        tableViewMessages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setLabels(newValue);
            }

            private void setLabels(Object newValue)
            {
                StudentMessage sm = (StudentMessage) newValue;
                Student student = model.getStudents(sm.getStudentId());
                AttendanceStatus as = model.getAttendanceStatusBasedOnId(sm.getStudentId(), sm.getAttendanceHistoryId());
                lblDate.setText("Date: " + as.getDateAsLocalDate().toString());
                lblFrom.setText("From: " + student.getName());
                lblRequest.setText("Request to change to: " + sm.getStatus());
                txtAreaMessage.setText(sm.getMessage());
            }
        });
    }
}
