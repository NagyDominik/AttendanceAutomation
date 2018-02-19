package attendanceautomation.BE;

import java.util.Calendar;
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
    //private final FloatProperty absencePercentage = new SimpleFloatProperty();
    private float absencePercentage;
    private ObservableList<AbsenceStatus> absence = FXCollections.observableArrayList();
    
    public Student(String email, String name) {
        this.email = email;
        this.name = name;
        this.absencePercentage = 99.9f;
        setAbsenceMockData();
    }

    public float getAbsencePercentage()
    {
        return absencePercentage;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }
    
    private void setAbsenceMockData()
    {
        Calendar date = Calendar.getInstance();
        this.absence.add(new AbsenceStatus("CS2017_B", date, Boolean.TRUE));
        date.add(Calendar.HOUR, -24);
        this.absence.add(new AbsenceStatus("CS2017_B", date, Boolean.FALSE));
    }

    public ObservableList<AbsenceStatus> getAbsenceInfo()
    {
        return absence;
    }
 }

