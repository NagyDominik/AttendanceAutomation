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
    private Teacher recipient;
    private Student sender;
    private LocalDate date;
    private AttendanceStatus history;
    private String status;
    private String message;
    boolean hasBeenSeen;

    public StudentMessage(Teacher recipient, Student sender, LocalDate date, String status, String message, AttendanceStatus history)
    {
        this.recipient = recipient;
        this.sender = sender;
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

    public Teacher getRecipient()
    {
        return recipient;
    }

    public void setRecipient(Teacher recipient)
    {
        this.recipient = recipient;
    }

    public Student getSender()
    {
        return sender;
    }

    public void setSender(Student sender)
    {
        this.sender = sender;
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
}
