package attendanceautomation.BE;

import java.util.Calendar;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Represents a student
 *
 * @author sebok
 */
public class Student extends Person {

    private int id;
    private String email;
    private String name;
    private final FloatProperty presencePercentage = new SimpleFloatProperty();
    private ObservableList<AttendanceStatus> attendance = FXCollections.observableArrayList();

    public Student(String email, String name) {
        this.email = email;
        this.name = name;
        setAttendanceeMockData();
        calculateAttPer();
    }

    public float getPresencePercentage() {
        this.calculateAttPer();
        return presencePercentage.get();
    }

    public void setPresencePercentage(float value) {
        presencePercentage.set(value);
    }

    public FloatProperty presencePercentageProperty() {
        return presencePercentage;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    /**
     * Create mock attendance data for the student
     */
    private void setAttendanceeMockData() {
        Calendar date = Calendar.getInstance();
        this.attendance.add(new AttendanceStatus("CS2017_B", date, Boolean.TRUE));
        date.add(Calendar.HOUR, -24);
        this.attendance.add(new AttendanceStatus("CS2017_B", date, Boolean.FALSE));
        date.add(Calendar.HOUR, -24);
        this.attendance.add(new AttendanceStatus("CS2017_B", date, Boolean.TRUE));
    }

    public ObservableList<AttendanceStatus> getAttendanceInfo() {
        return attendance;
    }

    private void calculateAttPer() {
        int presCount = 0;
        for (AttendanceStatus attendanceStatus : attendance) {
            if (attendanceStatus.getStatus().equals("Present")) {
                presCount++;
            }
        }
        presencePercentage.set((float) presCount / attendance.size() * 100);
    }
    
}
