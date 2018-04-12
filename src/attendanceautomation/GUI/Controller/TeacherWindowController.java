package attendanceautomation.GUI.Controller;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView imgViewMessage;

    private Model model;
    private final Image message = new Image("img/message.png");
    private final Image newMessage = new Image("img/newMessage.png");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            model = Model.getInstance();
            if (model.getCurrentUser() == null) {
                model.attemptLocalLogin();
            }
            startdatePicker.setValue(LocalDate.of(LocalDate.now().getYear() - 1, Month.AUGUST, 15));
            enddatePicker.setValue(LocalDate.now());
            teacherNameLbl.setText(model.getCurrentUser().getName());
            classChoiceBox.setItems(model.getClassData());
            classChoiceBox.getSelectionModel().selectFirst();
            studentsTV.setItems(classChoiceBox.getSelectionModel().getSelectedItem().getParticipants());
            setCellValueFactories();
            addListenersAndHandlers();
            calculateAttendance();
            setMessageIcon();
        }
        catch (ModelException ex) {
            Logger.getLogger(TeacherWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logoutClicked(ActionEvent event) {
        try {
            model.clearLocalData();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/LoginWindow.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) teacherNameLbl.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException | ModelException ex) {
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
            AlertWindow.showAlert(ex);
        }
    }

    @FXML
    private void startdateSelected(ActionEvent event) {
        if (startdatePicker.getValue().minusDays(1).isBefore(enddatePicker.getValue())) {
            calculateAttendance();
        } else {
            AlertWindow.showAlert(new Exception("The start date cannot be set after the end date!"));
        }
    }

    @FXML
    private void enddateSelected(ActionEvent event) {
        if (enddatePicker.getValue().plusDays(1).isAfter(startdatePicker.getValue())) {
            calculateAttendance();
        } else {
            AlertWindow.showAlert(new Exception("The end date cannot be set before the end date!"));
        }
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
        studentNameCol.setCellValueFactory(new PropertyValueFactory("name"));
        studentAbsenceCol.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().getPercentageStringProperty());
        stattodayCol.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().getTodaysStatusProperty());
    }

    private void addListenersAndHandlers() {
        classChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                studentsTV.setItems(classChoiceBox.getSelectionModel().getSelectedItem().getParticipants());
                calculateAttendance();
            }
        }
        );
    }

    private void calculateAttendance() {
        for (Student s : classChoiceBox.getSelectionModel().getSelectedItem().getParticipants()) {
            LocalDate start = startdatePicker.getValue().minusDays(1);
            LocalDate end = enddatePicker.getValue().plusDays(1);
            s.calculateAttPer(start, end);
        }
        studentsTV.refresh();
    }

    @FXML
    private void btnMessagesClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/TeacherMessage.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            imgViewMessage.setImage(message);

        }
        catch (IOException ex) {
            Logger.getLogger(TeacherWindowController.class.getName()).log(Level.SEVERE, null, ex);
            AlertWindow.showAlert(ex);
        }
    }
    
    /**
     * Set the messages image view depending on the number of unread messages.
     */
    private void setMessageIcon() {
        try {
            if (model.hasUnreadMessage(model.getCurrentUser().getId())) {
                imgViewMessage.setImage(newMessage);
            } else {
                imgViewMessage.setImage(message);
            }
        }
        catch (ModelException ex) {
            AlertWindow.showAlert(ex);
        }
    }

}
