/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class AttendanceWindowController implements Initializable {

    @FXML
    private JFXTreeTableView historyTV;
    @FXML
    private Label teacherNameLbl;
    @FXML
    private Label percentageLbl;
    @FXML
    private Label studentNameLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void logoutClicked(ActionEvent event) {
    }

    @FXML
    private void backClicked(ActionEvent event) {
    }

    @FXML
    private void presentClicked(ActionEvent event) {
    }

    @FXML
    private void absentClicked(ActionEvent event) {
    }

}
