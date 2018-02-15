package attendanceautomation.BE;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.time.LocalDate;
import java.util.Map;

/**
 * Represents a student
 *
 * @author sebok
 */
public class Student extends RecursiveTreeObject<Student>{

    private int id;
    private LocalDate currentDate;
    private Map<LocalDate, Boolean> history;
    private String email;
    private String name;
    private float absencePercentage;

    public Student(LocalDate currentDate, String email, String name) {
        this.currentDate = currentDate;
        this.email = email;
        this.name = name;
        this.absencePercentage = 99.9f;
    }

    public String getEmail() {
        return email;
    }

    public float getAbsencePercentage()
    {
        return absencePercentage;
    }
    
    public String getName()
    {
        return this.name;
    }
 }
