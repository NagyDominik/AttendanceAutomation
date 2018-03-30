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
    private String className;
    private Teacher teacher;
    private ObservableList<Student> participants = FXCollections.observableArrayList();

    public ClassData() {
    }

    public ClassData(String className, Teacher teacher) {
        this.className = className;
        this.teacher = teacher;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParticipants(ObservableList<Student> participants) {
        this.participants = participants;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ObservableList<Student> getParticipants() {
        return participants;
    }

    public String getClassName() {
        return className;
    }

    public int getId() {
        return id;
    }

    public void addStudent(Student student) {
        this.participants.add(student);
    }

    @Override
    public String toString() {
        return className;
    }
    
}
