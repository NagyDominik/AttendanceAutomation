package attendanceautomation.BE;

import java.time.LocalDate;
import java.util.HashMap;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * Represents a student
 *
 * @author sebok
 */
public class Student extends Person {

    private int id;
    private LocalDate currentDate;
    private String email;
    private String name;
    private final FloatProperty absencePercentage = new SimpleFloatProperty();
    //private Map<LocalDate, Boolean> history = new HashMap();
    private final MapProperty<LocalDate, Boolean> history = new SimpleMapProperty(FXCollections.observableMap(new HashMap<LocalDate, Boolean>()));;
    
    public Student(LocalDate currentDate, String email, String name) {
        this.currentDate = currentDate;
        this.email = email;
        this.name = name;
        this.absencePercentage.set(99.9f);
    }

    public void addHistory() {
        this.history.put(LocalDate.now(), Boolean.FALSE);
    }

    public ObservableMap getHistory() {
        return history.get();
    }

    public void setHistory(ObservableMap value) {
        history.set(value);
    }

    public MapProperty historyProperty() {
        return history;
    }    

    public float getAbsencePercentage() {
        return absencePercentage.get();
    }

    public void setAbsencePercentage(float value) {
        absencePercentage.set(value);
    }

    public FloatProperty getAbsencePercentageProperty() {
        return absencePercentage;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return this.name;
    }
}
