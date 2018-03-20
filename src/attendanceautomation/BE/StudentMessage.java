package attendanceautomation.BE;

import java.time.LocalDate;
import java.util.Calendar;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Represents a message sent to a teacher by a student (usually asking for status change).
 * @author sebok
 */
public class StudentMessage
{
    int id;
    private int teacherId;
    private int studentId;
    private int attendanceHistoryId;
    private String status;
    private String message;
    private final BooleanProperty hasBeenSeen = new SimpleBooleanProperty();

    public StudentMessage(int teacherId, int studentId, String status, String message, int attendanceId)
    {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.attendanceHistoryId = attendanceId;
        this.status = status;
        this.message = message;
    }

    public boolean hasBeenSeen()
    {
        return hasBeenSeen.get();
    }

    public void setHasBeenSeen(boolean value)
    {
        hasBeenSeen.set(value);
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public int getTeacherId()
    {
        return teacherId;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public int getAttendanceHistoryId()
    {
        return attendanceHistoryId;
    }
}
