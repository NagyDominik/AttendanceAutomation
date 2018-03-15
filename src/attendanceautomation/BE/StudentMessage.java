package attendanceautomation.BE;

import java.util.Calendar;

/**
 * Represents a message sent to a teacher by a student (usually asking for status change).
 * @author sebok
 */
public class StudentMessage
{
    private Teacher recipient;
    private Student sender;
    private Calendar date;
    private String status;
    private String message;

    public StudentMessage(Teacher recipient, Student sender, Calendar date, String status, String message)
    {
        this.recipient = recipient;
        this.sender = sender;
        this.date = date;
        this.status = status;
        this.message = message;
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

    public Calendar getDate()
    {
        return date;
    }

    public void setDate(Calendar date)
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
}
