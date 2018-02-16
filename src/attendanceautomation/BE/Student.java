package attendanceautomation.BE;

import java.time.LocalDate;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.ObservableMap;

/**
 * Represents a student
 *
 * @author sebok
 */
public class Student extends Person{

    private int id;
    private LocalDate currentDate;
    private String email;
    private String name;
    //private final FloatProperty absencePercentage = new SimpleFloatProperty();
    private float absencePercentage;
    private final MapProperty<LocalDate, Boolean> history = new SimpleMapProperty<>();
    
    public Student(LocalDate currentDate, String email, String name) {
        this.currentDate = currentDate;
        this.email = email;
        this.name = name;
        this.absencePercentage = 99.9f;
        //this.absencePercentage.set(99.9f);
    }
    
    private ObservableMap getHistory() {
        return history.get();
    }

    private void setHistory(ObservableMap value) {
        history.set(value);
    }

    private MapProperty historyProperty() {
        return history;
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
 }
