package attendanceautomation;

import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import attendanceautomation.GUI.Model.Model;
import attendanceautomation.GUI.Model.ModelException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Model model;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = Model.getInstance();
        Parent root;
        if (model.attemptLocalLogin() instanceof Teacher) {
            root = FXMLLoader.load(getClass().getResource("GUI/View/TeacherWindow.fxml"));
        } else if (model.attemptLocalLogin() instanceof Student) {
            root = FXMLLoader.load(getClass().getResource("GUI/View/StudentWindow.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("GUI/View/LoginWindow.fxml"));
        }

        primaryStage.setTitle("EASV Attendance");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
