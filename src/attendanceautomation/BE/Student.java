package attendanceautomation.BE;

import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a student
 *
 * @author sebok
 */
public class Student extends Person{

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
        //this.absencePercentage.set(99.9f);
        setAbsenceMockData();
    }
  
   /* private float getAbsencePercentage() {
        return absencePercentage.get();
    }

    private void setAbsencePercentage(float value) {
        absencePercentage.set(value);
    }

    private FloatProperty absencePercentageProperty() {
        return absencePercentage;
    }    
*/
    public String getEmail() {
        return email;
    }
    
    public String getName()
    {
        return this.name;
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
