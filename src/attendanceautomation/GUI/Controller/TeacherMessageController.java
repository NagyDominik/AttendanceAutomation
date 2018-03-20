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
    ObservableList<StudentMessage> messages = FXCollections.observableArrayList();
    Model model = Model.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setUpTableColumn();
        setUpListeners();
        setRowFactory();
        try
        {
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
            AttendanceStatus as = model.getAttendanceStatusBasedOnId(param.getValue().getStudentId(), param.getValue().getAttendanceHistoryId());
            Student s = model.getStudents(param.getValue().getStudentId());
            return new ReadOnlyStringWrapper(as.getDate() + " " + s.getName());
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
                StudentMessage sm = (StudentMessage) newValue;
                setLabels(sm);
                sm.setHasBeenSeen(true);
                tableViewMessages.refresh();
            }

            private void setLabels(StudentMessage newValue)
            {
                Student student = model.getStudents(newValue.getStudentId());
                AttendanceStatus as = model.getAttendanceStatusBasedOnId(newValue.getStudentId(), newValue.getAttendanceHistoryId());
                lblDate.setText("Date: " + as.getDateAsLocalDate().toString());
                lblFrom.setText("From: " + student.getName());
                lblRequest.setText("Request to change to: " + newValue.getStatus());
                txtAreaMessage.setText(newValue.getMessage());
            }
        });
    }
}
