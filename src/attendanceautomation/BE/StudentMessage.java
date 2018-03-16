package attendanceautomation.BE;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * Represents a message sent to a teacher by a student (usually asking for status change).
 * @author sebok
 */
public class StudentMessage
{
    int id;
    private int teacherId;
    private int studentId;
    private LocalDate date;
    private AttendanceStatus history;
    private String status;
    private String message;
    boolean hasBeenSeen;

    public StudentMessage(int teacherId, int studentId, LocalDate date, String status, String message, AttendanceStatus history)
    {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.message = message;
        this.history = history;
        this.hasBeenSeen = false;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
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

    public AttendanceStatus getHistory()
    {
        return history;
    }

    public void setHistory(AttendanceStatus history)
    {
        this.history = history;
    }

    public boolean getHasBeenSeen()
    {
        return hasBeenSeen;
    }

    public int getTeacherId()
    {
        return teacherId;
    }

    public int getStudentId()
    {
        return studentId;
    }
    
    
}
