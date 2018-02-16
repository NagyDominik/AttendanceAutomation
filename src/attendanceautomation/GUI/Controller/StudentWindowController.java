/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Student;
import attendanceautomation.GUI.Model.Model;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
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
    private TableView<?> historyTV;
    @FXML
    private TableColumn<ClassData, String> studentClassCol;
    @FXML
    private TableColumn<Student, String> studentDateCol;
    @FXML
    private TableColumn<Student, String> studentStatusCol;
    
    private Model model = Model.getInstance();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCellValueFactories();
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
    }

    @FXML
    private void absentClicked(ActionEvent event) {
    }

    private void backClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/TeacherWindow.fxml"));
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

    private void setCellValueFactories() {
        studentClassCol.setCellValueFactory(new PropertyValueFactory("className"));
        studentDateCol.setCellValueFactory(new PropertyValueFactory(""));
        studentStatusCol.setCellValueFactory(new PropertyValueFactory(""));
    }

}
