/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.GUI.Controller;

import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Student;
import attendanceautomation.GUI.Model.Model;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class TeacherWindowController implements Initializable {

   

    Model m = Model.getInstance();
    private Label nameLbl;
    @FXML
    private TableColumn<?, ?> classTeacher;
    @FXML
    private TableView<?> studentsTV;
    @FXML
    private Label teacherNameLbl;
    @FXML
    private TableColumn<?, ?> studentName;
    @FXML
    private TableColumn<?, ?> absenceStudent;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCellValueFactories();
        
        ObservableList students = FXCollections.observableArrayList(m.getStudent());
        final TreeItem root = new RecursiveTreeItem<Student>(students, RecursiveTreeObject::getChildren);
        studentsTV.getColumns().addAll(studentName, absenceStudent);
        studentsTV.setRoot(root);
        studentsTV.setShowRoot(false);
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
    private void attendanceClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomation/GUI/View/AttendanceWindow.fxml"));
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

    private void setCellValueFactories()
    {
        nameTeacher = new JFXTreeTableColumn<>("Name");
        nameTeacher.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param)
            {
                return new SimpleStringProperty(param.getValue().getValue().getName());         
            }
        });
        
        absenceTeacher = new JFXTreeTableColumn<>(" % of Absence");
        absenceTeacher.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param)
            {
                return new SimpleStringProperty(Float.toString(param.getValue().getValue().getAbsencePercentage()));
            }
        });
    }

}
