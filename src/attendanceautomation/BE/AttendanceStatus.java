/**
 * Represents a
 */
package attendanceautomation.BE;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author sebok
 */
public class AttendanceStatus {

    private String className;
    private Calendar date;
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


    public AttendanceStatus(String className, Calendar date, Boolean status) {
        this.className = className;
        this.date = date;
        this.status.set(status);
    }

    public String getClassName() {
        return className;
    }

    public String getDate() {
        SimpleDateFormat format1 = new SimpleDateFormat("EEEE, dd MMMM, yyyy");
        String formatted = format1.format(date.getTime());
        return formatted;
    }

    public void setStatus(Boolean status) {
        this.status.set(status);
    }
    
    public String getStatus() {
        return status.getValue() ? "Present" : "Absent";
    }
    
    public Boolean getStatusAsBoolean()
    {
        return status.getValue();
    }

}