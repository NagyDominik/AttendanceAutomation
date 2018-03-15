/**
 * Represents a
 */
package attendanceautomation.BE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author sebok
 */
public class AttendanceStatus {

   // private String className;
    private ClassData classData;
    private LocalDate date;
    private final BooleanProperty status = new SimpleBooleanProperty();

    private boolean isStatus() {
        return status.get();
    }

    private void setStatus(boolean value) {
        status.set(value);
    }

    private BooleanProperty statusProperty() {
        return status;
    }

    private boolean teacherSet = Boolean.FALSE;

    public boolean isTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(boolean teacherSet) {
        this.teacherSet = teacherSet;
    }

    public AttendanceStatus(ClassData data, LocalDate date, Boolean status) {
        this.classData = data;
        this.date = date;
        this.status.set(status);
    }

    public String getClassName() {
        return classData.getClassName();
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    public LocalDate getDateAsLocalDate()
    {
        return this.date;
    }
    
    public void setStatus(Boolean status) {
        this.status.set(status);
    }

    public String getStatus() {
        return status.getValue() ? "Present" : "Absent";
    }

    public Boolean getStatusAsBoolean() {
        return status.getValue();
    }

    public ClassData getClassData()
    {
        return classData;
    }
}
