/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.Student;
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

    private Model model = Model.getInstance();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameLbl.setText(model.getCurrentUser().getName());
        setCellValueFactories();
        Student user = (Student) model.getCurrentUser();
        historyTV.setItems(user.getAttendanceInfo());
        percentageLbl.setText(percentageLbl.getText() + user.getPresencePercentage() + " %");
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
        setAttendanceStatus(true);
    }

    @FXML
    private void absentClicked(ActionEvent event) {
        setAttendanceStatus(false);
    }

    private void setCellValueFactories() {
        studentClassCol.setCellValueFactory(new PropertyValueFactory("className"));
        studentDateCol.setCellValueFactory(new PropertyValueFactory("date"));
        studentStatusCol.setCellValueFactory(new PropertyValueFactory("status"));
    }

    private void setAttendanceStatus(Boolean status) {
        AttendanceStatus selStat = historyTV.getSelectionModel().getSelectedItem();
        if (!historyTV.getSelectionModel().getSelectedItem().isTeacherSet()) {
            selStat.setStatus(status);
        } else {
            Model.newAlert(new Exception("Your teacher set your status. Please contact him to modify it!"));
        }
    }

}
