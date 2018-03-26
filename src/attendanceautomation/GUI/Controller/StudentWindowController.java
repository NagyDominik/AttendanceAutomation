package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import attendanceautomation.GUI.AlertWindow;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
import com.jfoenix.controls.JFXButton;
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
public class StudentWindowController implements Initializable {

    @FXML
    private Label nameLbl;
    @FXML
    private Label percentageLbl;
    @FXML
    private TableView<AttendanceStatus> historyTV;
    @FXML
    private TableColumn<AttendanceStatus, String> studentClassCol;
    @FXML
    private TableColumn<AttendanceStatus, String> studentDateCol;
    @FXML
    private TableColumn<AttendanceStatus, String> studentStatusCol;
    @FXML
    private JFXButton btnRequestStatusChange;
    @FXML
    private JFXButton btnPresent;
    @FXML
    private JFXButton btnAbsent;

    private Model model;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            model = Model.getInstance();
            nameLbl.setText(model.getCurrentUser().getName());
            setCellValueFactories();
            setEvents();
            Student user = (Student) model.getCurrentUser();
            historyTV.setItems(user.getAttendanceInfo());
            btnRequestStatusChange.setDisable(true);
            setPercentage();
        }
        catch (ModelException ex) {
            Logger.getLogger(StudentWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logoutClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/LoginWindow.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) nameLbl.getScene().getWindow();
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
        setAttendanceStatus(1);
    }

    @FXML
    private void absentClicked(ActionEvent event) {
        setAttendanceStatus(0);
    }

    @FXML
    private void optionsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/OptionsWindow.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ex) {
            Logger.getLogger(StudentWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setCellValueFactories() {
        studentClassCol.setCellValueFactory(new PropertyValueFactory("className"));
        studentDateCol.setCellValueFactory(new PropertyValueFactory("date"));
        studentStatusCol.setCellValueFactory(new PropertyValueFactory("status"));
    }

    private void setAttendanceStatus(int status) {
        AttendanceStatus selStat = historyTV.getSelectionModel().getSelectedItem();
        if (!historyTV.getSelectionModel().getSelectedItem().isTeacherSet()) {
            selStat.setStatus(status);
            setPercentage();
            historyTV.refresh();
        } else {
            AlertWindow.showAlert(new Exception("Your teacher set your status. Please contact him to modify it!"));
        }
    }

    @FXML
    private void btnStatusChangeRequestClicked(ActionEvent event) {
        Teacher selectedTeacher = historyTV.getSelectionModel().getSelectedItem().getClassData().getTeacher();
        AttendanceStatus stat = historyTV.getSelectionModel().getSelectedItem();

        try {
            if (selectedTeacher == null) {
                throw new Exception("No class selected!");
            }

            model.setSelectedTeacher(selectedTeacher);
            model.setSelectedAttendanceInfo(stat);
            model.setSelectedStudent((Student) model.getCurrentUser());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/StudentMessageFXML.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) nameLbl.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception ex) {
            Logger.getLogger(TeacherWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setEvents() {
        this.historyTV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                if (newSelection.getDateAsLocalDate().isEqual(LocalDate.now())) {
                    btnRequestStatusChange.setDisable(true);
                    btnAbsent.setDisable(false);
                    btnPresent.setDisable(false);

                } else {
                    btnRequestStatusChange.setDisable(false);
                    btnAbsent.setDisable(true);
                    btnPresent.setDisable(true);
                }
            }
        });
    }

    private void setPercentage() {
        Student user = (Student) model.getCurrentUser();
        user.calculateAttPer();
        percentageLbl.setText("Total percentage of participation: " + user.getPercentageStringProperty().getValue() + " %");
    }

}
