package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.StudentMessage;
import attendanceautomation.GUI.AlertWindow;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
        try
        {
            messages.addAll(model.getMessages(model.getCurrentUser().getId()));
        } catch (ModelException ex)
        {
            AlertWindow.showAlert(ex);
        }
    }    
    
}
