package attendanceautomation.GUI.Model;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.BE.Teacher;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebok
 */
public class ModelTest
{
    
    public ModelTest()
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
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getInstance method, of class Model.
     */
    @Test
    public void testGetInstance() throws Exception
    {
        System.out.println("getInstance");
        Model expResult = Model.getInstance();
        Model result = Model.getInstance();
        assertEquals(expResult, result);
        expResult = null;
    }


    /**
     * Test of getCurrentUser method, of class Model.
     */
    @Test
    public void testGetCurrentUser() throws ModelException
    {
        System.out.println("getCurrentUser");
        Model instance = Model.getInstance();
        Person user = new Teacher("jml@easv.dk", "JML", 0);
        Person expResult = user;
        instance.setCurrentUser(user);
        Person result = instance.getCurrentUser();
        assertEquals(expResult, result);
        instance = null;
    }

    /**
     * Test of setSelectedStudent method, of class Model.
     */
    @Test
    public void testSetSelectedStudent() throws ModelException
    {
        System.out.println("setSelectedStudent");
        Student s = new Student("spt@easv.dk", "spt", 0);
        Student selected = s;
        Model instance = Model.getInstance();
        instance.setSelectedStudent(selected);
        assertEquals(s, instance.getSelectedStudent());
        instance = null;
    }

    /**
     * Test of getSelectedStudent method, of class Model.
     */
    @Test
    public void testGetSelectedStudent() throws ModelException
    {
        System.out.println("getSelectedStudent");
        Student s = new Student("spt@easv.dk", "spt", 0);
        Model instance = Model.getInstance();
        Student expResult = s;
        instance.setSelectedStudent(s);
        Student result = instance.getSelectedStudent();
        assertEquals(expResult, result);
        instance = null;
    }

    /**
     * Test of setSelectedTeacher method, of class Model.
     */
    @Test
    public void testSetSelectedTeacher() throws ModelException
    {
        System.out.println("setSelectedTeacher");
        Teacher selectedTeacher = new Teacher("jml@easv.dk", "JML", 0);
        Model instance = Model.getInstance();
        instance.setSelectedTeacher(selectedTeacher);
        assertEquals(selectedTeacher, instance.getSelectedTeacher());
        instance = null;
    }

    /**
     * Test of getSelectedTeacher method, of class Model.
     */
    @Test
    public void testGetSelectedTeacher() throws ModelException
    {
        System.out.println("getSelectedTeacher");
        Model instance = Model.getInstance();
        Teacher expResult = new Teacher("jml@easv.dk", "JML", 0);
        instance.setSelectedTeacher(expResult);
        Teacher result = instance.getSelectedTeacher();
        assertEquals(expResult, result);
        instance = null;
    }

    /**
     * Test of setSelectedAttendanceInfo method, of class Model.
     */
    @Test
    public void testSetSelectedAttendanceInfo() throws ModelException
    {
        System.out.println("setSelectedAttendanceInfo");
        AttendanceStatus stat = new AttendanceStatus(0, LocalDate.now(), 0, false);
        Model instance = Model.getInstance();
        instance.setSelectedAttendanceInfo(stat);
        assertEquals(stat, instance.getSelectedAttendanceStatus());
        instance = null;
    }

    /**
     * Test of getSelectedAttendanceStatus method, of class Model.
     */
    @Test
    public void testGetSelectedAttendanceStatus() throws ModelException
    {
        System.out.println("getSelectedAttendanceStatus");
        Model instance = Model.getInstance();
        AttendanceStatus expResult = new AttendanceStatus(0, LocalDate.now(), 0, false);
        instance.setSelectedAttendanceInfo(expResult);
        AttendanceStatus result = instance.getSelectedAttendanceStatus();
        assertEquals(expResult, result);
        instance = null;
    }

    /**
     * Test of setCurrentUser method, of class Model.
     */
    @Test
    public void testSetCurrentUser() throws ModelException
    {
        System.out.println("setCurrentUser");
        Person user = new Student("spt@easv.sk", "SPT", 0);
        Model instance = Model.getInstance();
        instance.setCurrentUser(user);
        assertEquals(user, instance.getCurrentUser());
        instance = null;
    }
    
}
