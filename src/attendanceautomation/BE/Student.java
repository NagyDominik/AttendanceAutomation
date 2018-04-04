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
    private int classID; // The ID of the class the student takes place in.

    public Student(String email, String name, int id) {
        super(id, name, email);
    }

    public Student() {
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
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

    /**
     * If the student has an entry for today's attendance, return its status.
     * @return The status of today's attendance, if there is one. Otherwise it returns "N/A", indicating that the student has no entry for today's attendance.
     */
    public StringProperty getTodaysStatusProperty() {
        for (AttendanceStatus attendanceStatus : attendance)
        {
            if (attendanceStatus.getDateAsLocalDate().isEqual(LocalDate.now()))
            {
                return new SimpleStringProperty(attendanceStatus.getStatus());
            }
        }
        
        return new SimpleStringProperty("N/A");
    }

    public ObservableList<AttendanceStatus> getAttendanceInfo() {
        return attendance;
    }

    /**
     * Calculate the total attendance percentage for this student.
     */
    public void calculateAttPer() {
        int presCount = 0;
        for (AttendanceStatus attendanceStatus : attendance) {
            if (attendanceStatus.getStatusAsNumber() == 1) {
                presCount++;
            }
        }
        presencePercentage.set((float) presCount / attendance.size() * 100);
    }

    /**
     * Calculate the attendance percentage between the start and end dates.
     * @param start The start date.
     * @param end  The end date.
     */
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

    public void addHistory(AttendanceStatus history) {
        this.attendance.add(history);
    }
}
