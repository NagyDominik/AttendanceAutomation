package attendanceautomation.BE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents the attendance on a given day.
 * @author sebok
 */
public class AttendanceStatus {

    private int id;
    private LocalDate date;
    private final IntegerProperty status = new SimpleIntegerProperty();
    private boolean teacherSet = Boolean.FALSE;

    public AttendanceStatus(int id, LocalDate date,int status, boolean teacherSet) {
        this.id = id;
        this.date = date;
        this.status.set(status);
        this.teacherSet = teacherSet;
    }

    public AttendanceStatus() {
    }
    
    public AttendanceStatus(LocalDate date) {
        this.date = date;
    }

    public boolean isTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(boolean teacherSet) {
        this.teacherSet = teacherSet;
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
        return status.getValue().equals(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
