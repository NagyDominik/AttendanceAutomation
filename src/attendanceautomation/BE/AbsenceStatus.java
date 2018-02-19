/**
 * Represents a 
 */
package attendanceautomation.BE;

import java.util.Calendar;

/**
 *
 * @author sebok
 */
public class AbsenceStatus
{
    String className;
    Calendar date;
    Boolean absent;

    public AbsenceStatus(String className, Calendar date, Boolean absent)
    {
        this.className = className;
        this.date = date;
        this.absent = absent;
    }

    public String getClassName()
    {
        return className;
    }

    public String getDate()
    {
        return date.getTime().toString();
    }

    public String getAbsent()
    {
        return absent ? "Absent" : "Present";
    }
    
    

}
