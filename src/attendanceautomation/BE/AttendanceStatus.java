package attendanceautomation.BE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author sebok
 */
public class AttendanceStatus {

    private int id;
    private ClassData classData;
    private LocalDate date;
    private IntegerProperty status = new SimpleIntegerProperty();
    private boolean teacherSet = Boolean.FALSE;
    
    private Student st = new Student();
    private int studentID = st.getId();
    
    private Teacher tc = new Teacher();
    private int teacherID = tc.getId();

    public AttendanceStatus(int id, LocalDate date, ClassData classData, int status, int studentID, int teacherID) {
        this.id = id;
        this.date = date;
        this.classData = classData;
        this.status.set(status);
        this.studentID = studentID;
        this.teacherID = teacherID;
    }

    public AttendanceStatus(ClassData classData, LocalDate date) {
        this.classData = classData;
        this.date = date;
    }

    public boolean isTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(boolean teacherSet) {
        this.teacherSet = teacherSet;
    }

    public String getClassName() {
        return classData.getClassName();
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("w, yyyy-MM-dd");
        return "Week " + date.format(formatter);
    }

    public LocalDate getDateAsLocalDate() {
        return this.date;
    }

    public void setStatus(Integer status) {
        this.status.set(status);
    }

    public int getStatusAsNumber() {
        return status.getValue();
    }

    public String getStatus() {
        switch (status.getValue()) {
            case 0:
                return "🗙";
            case 1:
                return "✔";
            default:
                return "-";
        }
    }

    public Boolean getStatusAsBoolean() {
        return status.equals(1);
    }

    public ClassData getClassData() {
        return classData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getStudentID() {
        return studentID;
    }

    public int getTeacherID() {
        return teacherID;
    }
}