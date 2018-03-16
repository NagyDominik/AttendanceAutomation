/**
 * Represents the attendance on a given date.
 */
package attendanceautomation.BE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author sebok
 */
public class AttendanceStatus {

    private int id;
    private ClassData classData;
    private LocalDate date;

    private IntegerProperty status = new SimpleIntegerProperty() ;
    private boolean teacherSet = Boolean.FALSE;

    public AttendanceStatus(ClassData classData, LocalDate date, int status) {
        this.classData = classData;
        this.date = date;
        this.status.set(status);
    }

    public AttendanceStatus(ClassData classData, LocalDate date) {
        this.classData = classData;
        this.date = date;
    }
    
    

    private int isStatus() {
        return status.get();
    }

    private void setStatus(int value) {
        status.set(value);
    }

    private IntegerProperty statusProperty() {
        return status;
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
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    public LocalDate getDateAsLocalDate()
    {
        return this.date;
    }
    
    public void setStatus(Integer status) {
        this.status.set(status);
    }

    public String getStatus() {
                
        switch(status.getValue()){        
            case 0 : return "🗙";
            case 1 : return "✔";
            default: return "-";
        }
    }

    public Boolean getStatusAsBoolean() {
        if(status.equals(1)){
            return true;
        }else{
            return false;
        }
    }

    public ClassData getClassData()
    {
        return classData;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
