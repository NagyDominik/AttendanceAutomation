package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Student;
import attendanceautomation.GUI.AlertWindow;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.ImageView;
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
    @FXML
    private JFXDatePicker startdatePicker;
    @FXML
    private JFXDatePicker enddatePicker;
    @FXML
    private ImageView imgViewStudentImage;
    
    private Model model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            model = Model.getInstance();
            startdatePicker.setValue(LocalDate.of(LocalDate.now().getYear() - 1, Month.AUGUST, 15));
            enddatePicker.setValue(LocalDate.now());
            teacherNameLbl.setText(model.getCurrentUser().getName());
            studentNameLbl.setText(model.getSelectedStudent().getName());
            setUpCellValueFactories();
            historyTV.setItems(model.getSelectedStudent().getAttendanceInfo());
            imgViewStudentImage.setImage(model.getSelectedStudent().getImage());
            filterDates();
            
        }
        catch (ModelException ex) {
            Logger.getLogger(AttendanceWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        setAttendanceStatus(1);
    }

    @FXML
    private void absentClicked(ActionEvent event) {
        setAttendanceStatus(0);
    }

    @FXML
    private void startdateSelected(ActionEvent event) {
        if (startdatePicker.getValue().minusDays(1).isBefore(enddatePicker.getValue())) {
            filterDates();
        } else {
            AlertWindow.showAlert(new Exception("The start date cannot be set after the end date!"));
        }
    }

    @FXML
    private void enddateSelected(ActionEvent event) {
        if (enddatePicker.getValue().plusDays(1).isAfter(startdatePicker.getValue())) {
            filterDates();
        } else {
            AlertWindow.showAlert(new Exception("The end date cannot be set before the end date!"));
        }
    }

    private void setUpCellValueFactories() {
        teacherClassCol.setCellValueFactory(new PropertyValueFactory("")); //TODO
        teacherDateCol.setCellValueFactory(new PropertyValueFactory("date"));
        teacherStatusCol.setCellValueFactory(new PropertyValueFactory("status"));
    }

    private void setAttendanceStatus(int status) {
        try {
            AttendanceStatus selStat = historyTV.getSelectionModel().getSelectedItem();
            selStat.setStatus(status);
            selStat.setTeacherSet(true);
            filterDates();
            model.saveAttendance(selStat, model.getSelectedStudent());
            historyTV.refresh();
        } catch (ModelException ex) {
            Logger.getLogger(AttendanceWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void filterDates() {
        LocalDate start = startdatePicker.getValue().minusDays(1);
        LocalDate end = enddatePicker.getValue().plusDays(1);
        historyTV.setItems(model.filterStudentHistory(start, end));
        calculateAttendance(start, end);
    }

    private void calculateAttendance(LocalDate start, LocalDate end) {
        model.getSelectedStudent().calculateAttPer(start, end);
        percentageLbl.setText("Total percentage of participation: " + model.getSelectedStudent().getPercentageStringProperty().getValue() + " %");
    }
}
