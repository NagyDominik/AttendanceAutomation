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
       // System.out.println(date.getTime());
        this.attendance.add(new AttendanceStatus(new ClassData("CS2017_B", new Teacher("teacher1@easv.dk", "Teacher 1")), date, Boolean.TRUE));
        date.add(Calendar.DAY_OF_MONTH, -1);
       // System.out.println(date.getTime());
        this.attendance.add(new AttendanceStatus(new ClassData("CS2017_B", new Teacher("teacher1@easv.dk", "Teacher 1")), date, Boolean.FALSE));
        date.add(Calendar.DAY_OF_MONTH, -1);
       // System.out.println(date.getTime());
        this.attendance.add(new AttendanceStatus(new ClassData("CS2017_B", new Teacher("teacher2@easv.dk", "Teacher 2")), date, Boolean.TRUE));
        
        for (AttendanceStatus attendanceStatus : attendance)
        {
            System.out.println("The current date is : " + attendanceStatus.getDateAsCalendar().getTime());
        }
    }

    public ObservableList<AttendanceStatus> getAttendanceInfo() {
//        for (AttendanceStatus attendanceStatus : attendance)
//        {
//            System.out.println("The current date is : " + attendanceStatus.getDateAsCalendar().getTime());
//        }
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
