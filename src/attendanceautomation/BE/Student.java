package attendanceautomation.BE;

import java.text.DecimalFormat;
import java.time.LocalDate;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Represents a student
 *
 * @author sebok
 */
public class Student extends Person {

    private final FloatProperty presencePercentage = new SimpleFloatProperty();
    private ObservableList<AttendanceStatus> attendance = FXCollections.observableArrayList();
    
    public Student(String email, String name, int id) {
        super(id, name, email);
    }

    public Student()
    {
    }
    
    public float getPresencePercentage() {
        return presencePercentage.get();
    }
    
    public StringProperty getPercentageStringProperty() {
        StringProperty property = new SimpleStringProperty();
        DecimalFormat df = new DecimalFormat("#.##");
        String formated = df.format(presencePercentage.get());
        property.set(formated);
        return property;
    }

    public StringProperty getTodaysStatusProperty() {
        StringProperty property = new SimpleStringProperty();
        if (attendance.size() > 0)
        {
            property.set(attendance.get(0).getStatus());
        }
        else
        {
            property.set("0");
        }
        return property;
    }

    public void setPresencePercentage(float value) {
        presencePercentage.set(value);
    }

    public FloatProperty presencePercentageProperty() {
        return presencePercentage;
    }

    public ObservableList<AttendanceStatus> getAttendanceInfo() {
        return attendance;
    }

    public void calculateAttPer() {
        int presCount = 0;
        for (AttendanceStatus attendanceStatus : attendance) {
            if (attendanceStatus.getStatusAsNumber() == 1) {
                presCount++;
            }
        }
        presencePercentage.set((float) presCount / attendance.size() * 100);
    }

    public void calculateAttPer(LocalDate start, LocalDate end) {
        int presCount = 0;
        int size = 0;
        for (AttendanceStatus attendanceStatus : attendance) {
            LocalDate date = attendanceStatus.getDateAsLocalDate();
            if (date.isAfter(start) && date.isBefore(end)) {
                size++;
                if (attendanceStatus.getStatusAsNumber() == 1) {
                    presCount++;
                }
            }
        }
        if (size > 0) {
            presencePercentage.set((float) presCount / size * 100);
        } else {
            presencePercentage.set(0);
        }

    }
}
