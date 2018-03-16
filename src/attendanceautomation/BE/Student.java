package attendanceautomation.BE;

import java.time.LocalDate;
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

    public Student(String email, String name, int id) {
        this.email = email;
        this.name = name;
        this.id = id;
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

    public int getId()
    {
        return id;
    }
    
    /**
     * Create mock attendance data for the student
     */
    private void setAttendanceeMockData() {
        attendance.add(new AttendanceStatus(new ClassData("CS2017_B", new Teacher("teacher@easv.dk", "Teacher",0)), LocalDate.now(), 1));
        attendance.add(new AttendanceStatus(new ClassData("CS2017_B", new Teacher("teacher@easv.dk", "Teacher",0)), LocalDate.parse("2018-03-14"), -1));
        attendance.add(new AttendanceStatus(new ClassData("CS2017_B", new Teacher("teacher@easv.dk", "Teacher",0)), LocalDate.parse("2018-03-13")));

    }

    public ObservableList<AttendanceStatus> getAttendanceInfo() {
        return attendance;
    }

    private void calculateAttPer() {
        int presCount = 0;
        for (AttendanceStatus attendanceStatus : attendance) {
            if (attendanceStatus.equals(1)) {
                presCount++;
            }
        }
        presencePercentage.set((float) presCount / attendance.size() * 100);
    }

}
