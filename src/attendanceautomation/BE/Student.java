package attendanceautomation.BE;

import java.time.LocalDate;
import java.util.Map;

/**
 * Represents a student
 * @author sebok
 */
public class Student
{
    private int id;
    private LocalDate currentDate;
    private Map<LocalDate, Boolean> history;

    public Student(LocalDate currentDate)
    {
        this.currentDate = currentDate;
    }
}
