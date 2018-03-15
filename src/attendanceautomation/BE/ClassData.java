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
    private ObservableList<Student> participants = FXCollections.observableArrayList();
    private String className;

    public ClassData(String className) {
        this.className = className;
    }

    public ObservableList<Student> getParticipants() {
        return participants;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return className;
    }

}
