package attendanceautomation.BE;

import java.time.LocalDate;
import java.util.Map;

/**
 * Represents a student
 *
 * @author sebok
 */
public class Student {

    private int id;
    private LocalDate currentDate;
    private Map<LocalDate, Boolean> history;
    private String email;

    public Student(LocalDate currentDate, String email) {
        this.currentDate = currentDate;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
