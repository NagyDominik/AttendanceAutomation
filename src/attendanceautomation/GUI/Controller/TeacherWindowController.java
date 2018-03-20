package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Student;
import attendanceautomation.GUI.AlertWindow;
import attendanceautomation.GUI.Model.Model;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
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
public class TeacherWindowController implements Initializable {

    @FXML
    private TableView<Student> studentsTV;
    @FXML
    private Label teacherNameLbl;
    @FXML
    private TableColumn<Student, String> studentNameCol;
    @FXML
    private TableColumn<Student, String> studentAbsenceCol;
    @FXML
    private TableColumn<Student, String> stattodayCol;
    @FXML
    private ChoiceBox<ClassData> classChoiceBox;
    @FXML
    private JFXDatePicker startdatePicker;
    @FXML
    private JFXDatePicker enddatePicker;
    private Model model = Model.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        teacherNameLbl.setText(model.getCurrentUser().getName());
        studentsTV.setItems(model.getStudents());
        calculateAttendance();                                          //Look into it later.
        classChoiceBox.setItems(model.getClassData());
        classChoiceBox.getSelectionModel().selectFirst();
        setCellValueFactories();
        addListenersAndHandlers();
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
    private void attendanceClicked(ActionEvent event) {
        try {
            Student selected = studentsTV.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new Exception("No selected stundent! Please selecet a student!");
            }
            model.setSelectedStudent(selected);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/AttendanceWindow.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) teacherNameLbl.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception ex) {
            Logger.getLogger(TeacherWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void startdateSelected(ActionEvent event) {
        filterDates();
    }

    @FXML
    private void enddateSelected(ActionEvent event) {
        filterDates();
    }

    private void setCellValueFactories() {
        studentNameCol.setCellValueFactory(new PropertyValueFactory("name"));
        studentAbsenceCol.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().getPercentageStringProperty());
        stattodayCol.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().getTodaysStatusProperty());
    }

    private void addListenersAndHandlers() {
        classChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                studentsTV.setItems(classChoiceBox.getSelectionModel().getSelectedItem().getParticipants());
            }
        }
        );
    }

    private void calculateAttendance() {
        for (Student s : model.getStudents()) {
            s.getPresencePercentage();
        }
    }

    @FXML
    private void btnMessagesClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/TeacherMessage.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ex) {
            Logger.getLogger(TeacherWindowController.class.getName()).log(Level.SEVERE, null, ex);
            AlertWindow.showAlert(ex);
        }
    }
    private void filterDates() {
        LocalDate startDate = startdatePicker.getValue();
        LocalDate endDate = enddatePicker.getValue();
        model.filterStudentHistory(startDate, endDate);
    }
    
}
