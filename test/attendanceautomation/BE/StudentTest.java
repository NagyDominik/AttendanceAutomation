package attendanceautomation.BE;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Student class.
 * @author sebok
 */
public class StudentTest
{
    
    public StudentTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }

    /**
     * Test of getClassID method, of class Student.
     */
    @Test
    public void testGetClassID()
    {
        System.out.println("getClassID");
        Student instance = new Student();
        instance.setClassID(0);
        int expResult = 0;
        int result = instance.getClassID();
        assertEquals(expResult, result);
       
        instance.setClassID(1);
        expResult = 1;
        result = instance.getClassID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setClassID method, of class Student.
     */
    @Test
    public void testSetClassID()
    {
        System.out.println("setClassID");
        Student instance = new Student();
        instance.setClassID(0);
        int expResult = 0;
        int result = instance.getClassID();
        assertEquals(expResult, result);
       
        instance.setClassID(1);
        expResult = 1;
        result = instance.getClassID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPresencePercentage method, of class Student.
     */
    @Test
    public void testGetPresencePercentage()
    {
        System.out.println("getPresencePercentage");
        Student instance = new Student();
        AttendanceStatus stat1 = new AttendanceStatus();
        AttendanceStatus stat2 = new AttendanceStatus();
        stat1.setStatus(0);
        stat2.setStatus(1);
        instance.addHistory(stat1);
        instance.addHistory(stat2);
        instance.calculateAttPer();
        float expResult = 50F;
        float result = instance.getPresencePercentage();
        assertEquals(expResult, result, 0.0);

        AttendanceStatus stat3 = new AttendanceStatus();
        stat3.setStatus(1);
        instance.addHistory(stat3);
        instance.calculateAttPer();
        expResult = 66.66F;
        result = instance.getPresencePercentage();
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getPercentageStringProperty method, of class Student.
     */
    @Test
    public void testGetPercentageStringProperty()
    {
        System.out.println("getPercentageStringProperty");
        Student instance = new Student();
        AttendanceStatus stat1 = new AttendanceStatus();
        AttendanceStatus stat2 = new AttendanceStatus();
        stat1.setStatus(0);
        stat2.setStatus(1);
        instance.addHistory(stat1);
        instance.addHistory(stat2);
        instance.calculateAttPer();

        StringProperty expResult = new SimpleStringProperty();
        DecimalFormat df = new DecimalFormat("#.##");
        String formated = df.format(50);
        expResult.set(formated);

        StringProperty result = instance.getPercentageStringProperty();
        assertEquals(expResult.get(), result.get());

    }

    /**
     * Test of getTodaysStatusProperty method, of class Student.
     */
    @Test
    public void testGetTodaysStatusProperty()
    {
        System.out.println("getTodaysStatusProperty");
        Student instance = new Student();
        AttendanceStatus today = new AttendanceStatus(LocalDate.now());
        today.setStatus(1);
        instance.addHistory(today);
        
        StringProperty expResult = new SimpleStringProperty("✔");
        StringProperty result = instance.getTodaysStatusProperty();
        assertEquals(expResult.get(), result.get());

    }

    /**
     * Test of getAttendanceInfo method, of class Student.
     */
    @Test
    public void testGetAttendanceInfo()
    {
        System.out.println("getAttendanceInfo");
        Student instance = new Student();
        ObservableList<AttendanceStatus> expResult = FXCollections.observableArrayList();
        
        AttendanceStatus att1 = new AttendanceStatus();
        AttendanceStatus att2 = new AttendanceStatus();
        instance.addHistory(att1);
        instance.addHistory(att2);
        
        expResult.add(att1);
        expResult.add(att2);
        
        ObservableList<AttendanceStatus> result = instance.getAttendanceInfo();
        assertEquals(expResult, result);

    }

    /**
     * Test of calculateAttPer method, of class Student.
     */
    @Test
    public void testCalculateAttPer_0args()
    {
        System.out.println("calculateAttPer");
        Student instance = new Student();
        AttendanceStatus stat1 = new AttendanceStatus();
        AttendanceStatus stat2 = new AttendanceStatus();
        stat1.setStatus(0);
        stat2.setStatus(1);
        instance.addHistory(stat1);
        instance.addHistory(stat2);
        instance.calculateAttPer();
        float expResult = 50F;
        float result = instance.getPresencePercentage();
        assertEquals(expResult, result, 0.0);

        AttendanceStatus stat3 = new AttendanceStatus();
        stat3.setStatus(1);
        instance.addHistory(stat3);
        instance.calculateAttPer();
        expResult = 66.66F;
        result = instance.getPresencePercentage();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateAttPer method, of class Student.
     */
    @Test
    public void testCalculateAttPer_LocalDate_LocalDate()
    {
        System.out.println("calculateAttPer");
        LocalDate start = LocalDate.of(2018, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2018, Month.APRIL, 5);
        
        AttendanceStatus stat1 = new AttendanceStatus(LocalDate.of(2018, Month.MARCH, 1));
        AttendanceStatus stat2 = new AttendanceStatus(LocalDate.of(2018, Month.MARCH, 2));
        stat1.setStatus(0);
        stat2.setStatus(1);
       
        Student instance = new Student();
        
        instance.addHistory(stat1);
        instance.addHistory(stat2);
        
        instance.calculateAttPer(start, end);
        assertEquals(50F, instance.getPresencePercentage(), 0.0);
        
        AttendanceStatus stat3 = new AttendanceStatus(LocalDate.of(2017, Month.MARCH, 1));
        stat3.setStatus(1);
        instance.addHistory(stat3);
        instance.calculateAttPer(start, end);

        assertEquals(50F, instance.getPresencePercentage(), 0.0);

        start = LocalDate.of(2017, Month.JANUARY, 1);
        instance.calculateAttPer(start, end);
        
        assertEquals(66.66F, instance.getPresencePercentage(), 0.1);
    }

    /**
     * Test of addHistory method, of class Student.
     */
    @Test
    public void testAddHistory()
    {
        System.out.println("addHistory");
        AttendanceStatus history = new AttendanceStatus();
        Student instance = new Student();
        instance.addHistory(history);
        
        assertEquals(history, instance.getAttendanceInfo().get(0));
        
        AttendanceStatus history2 = new AttendanceStatus();
        instance.addHistory(history2);
        assertEquals(history2, instance.getAttendanceInfo().get(1));
        
    }
    
}
