package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.GUI.AlertWindow;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
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
    private ObservableList<StudentMessage> messages = FXCollections.observableArrayList();
    private Model model;
    private StudentMessage message;
    private AttendanceStatus attendatnceStatus;
    private Student student;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setUpTableColumn();
        setUpListeners();
        setRowFactory();
        try
        {
            model = Model.getInstance();
            messages.addAll(model.getMessages(model.getCurrentUser().getId()));
            tableViewMessages.setItems(messages);
        } catch (ModelException ex)
        {
            AlertWindow.showAlert(ex);
        }
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

    /**
     * Set up a cellValue factory so that the correct date and name is displayed.
     */
    private void setUpTableColumn() {
        tableColumnsMessages.setCellValueFactory((TableColumn.CellDataFeatures<StudentMessage, String> param) ->
        {
            attendatnceStatus = model.getAttendanceStatusBasedOnId(param.getValue().getStudentId(), param.getValue().getAttendanceHistoryId());
            student = model.getStudents(param.getValue().getStudentId());
            return new ReadOnlyStringWrapper(attendatnceStatus.getDate() + " " + student.getName());
        });
    }
    
    /**
     * Override the updateItem() method, so that the unseen messages are highlighted with bold font.
     */
    private void setRowFactory()
    {
        tableViewMessages.setRowFactory((TableView<StudentMessage> param) -> new TableRow<StudentMessage>()
        {
            @Override
            protected void updateItem(StudentMessage item, boolean empty)
            {
                super.updateItem(item, empty);
                if(item == null)
                {
                    setStyle("");
                }
                else if (!item.hasBeenSeen())
                {
                    setStyle("-fx-font-weight: bold");
                }
                else
                {
                    setStyle("");
                }
            }
        });
    }

    /**
     * Set up a listener and a method to automatically change the labels to reflect the contents of a newly selected message.
     */
    private void setUpListeners()
    {
        tableViewMessages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try
                {
                    message = (StudentMessage) newValue;
                    attendatnceStatus = model.getAttendanceStatusBasedOnId(message.getStudentId(), message.getAttendanceHistoryId());
                    setLabels(message);
                    message.setHasBeenSeen(true);
                    tableViewMessages.refresh();
                    model.updateMessage(message);
                } catch (ModelException ex)
                {
                    Logger.getLogger(TeacherMessageController.class.getName()).log(Level.SEVERE, null, ex);
                    AlertWindow.showAlert(ex);
                }
            }

            private void setLabels(StudentMessage newValue)
            {
                student = model.getStudents(newValue.getStudentId());
                attendatnceStatus = model.getAttendanceStatusBasedOnId(newValue.getStudentId(), newValue.getAttendanceHistoryId());
                lblDate.setText("Date: " + attendatnceStatus.getDateAsLocalDate().toString());
                lblFrom.setText("From: " + student.getName());
                lblRequest.setText("Request to change to: " + (newValue.getStatus()?"Present":"Absent"));
                txtAreaMessage.setText(newValue.getMessage());
            }
        });
    }

    @FXML
    private void btnAcceptClicked(ActionEvent event)
    {
        if (attendatnceStatus != null)
        {
            switch(attendatnceStatus.getStatusAsNumber())
            {
                case 0: //Status was swt to absent 
                    if (message.getStatus())
                    {
                        attendatnceStatus.setStatus(1);
                    }
                    break;
                case 1: //Status was set to present
                    if (!message.getStatus())
                    {
                        attendatnceStatus.setStatus(0);
                    }
                    break;
                case -1:    //Status was not set befor
                    if (message.getStatus())
                    {
                        attendatnceStatus.setStatus(1);
                    }
                    else
                    {
                        attendatnceStatus.setStatus(0);
                    }
                    break;
                default:
                    break;
            }
            attendatnceStatus.setTeacherSet(true);
//            try
//            {
//                model.updateAttendanceStatus(attendatnceStatus);  //Currently not working, because we use mock data, not the database.
//            } catch (ModelException ex)
//            {
//                AlertWindow.showAlert(ex);
//            }
        }
    }
}
