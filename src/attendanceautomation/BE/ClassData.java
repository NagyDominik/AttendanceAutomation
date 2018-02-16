package attendanceautomation.BE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a class of students
 *
 * @author sebok
 */
public class ClassData {

    private int id;
    private ObservableList<Student> participants;
    private String className;

    public ClassData(String className) {
        this.className = className;
        participants = FXCollections.observableArrayList();
    }

    public ObservableList<Student> getParticipants() {
        return participants;
    }

    public String getClassName() {
        return className;
    }

}
