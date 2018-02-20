package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.GUI.Model.Model;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class AttendanceWindowController implements Initializable {

    @FXML
    private Label teacherNameLbl;
    @FXML
    private Label percentageLbl;
    @FXML
    private Label studentNameLbl;
    @FXML
    private TableView<AttendanceStatus> historyTV;
    @FXML
    private TableColumn<AttendanceStatus, String> teacherClassCol;
    @FXML
    private TableColumn<AttendanceStatus, String> teacherDateCol;
    @FXML
    private TableColumn<AttendanceStatus, String> teacherStatusCol;

    private Model model = Model.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        teacherNameLbl.setText(model.getCurrentUser().getName());
        studentNameLbl.setText(model.getSelectedStudent().getName());
        setUpCellValueFactories();
        historyTV.setItems(model.getSelectedStudent().getAttendanceInfo());
        percentageLbl.setText(model.getSelectedStudent().getPresencePercentage() + " %");
    }

    @FXML
    private void logoutClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/LoginWindow.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) teacherNameLbl.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ex) {
            Logger.getLogger(TeacherWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/TeacherWindow.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) teacherNameLbl.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ex) {
            Logger.getLogger(TeacherWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void presentClicked(ActionEvent event) {
        setAttendanceStatus(true);
        percentageLbl.setText(Float.toString(caluclatePercentage()) + " %");
        historyTV.refresh();
    }

    @FXML
    private void absentClicked(ActionEvent event) {
        setAttendanceStatus(false);
        percentageLbl.setText(Float.toString(caluclatePercentage()) + " %");
        historyTV.refresh();
    }

    private void setUpCellValueFactories() {
        teacherClassCol.setCellValueFactory(new PropertyValueFactory("className"));
        teacherDateCol.setCellValueFactory(new PropertyValueFactory("date"));
        teacherStatusCol.setCellValueFactory(new PropertyValueFactory("status"));
    }
    
    private void setAttendanceStatus(Boolean status) {
        AttendanceStatus selStat = historyTV.getSelectionModel().getSelectedItem();
        selStat.setStatus(status);
        selStat.setTeacherSet(true);
    }
    
    private float caluclatePercentage()
    {
        int count = 0;
        for (AttendanceStatus as : historyTV.getItems())
        {
            if (as.getStatusAsBoolean())
            {
                count++;
            }
        }
        
        return count/(float)historyTV.getItems().size() * 100;
    }
}
