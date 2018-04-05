package attendanceautomation.BE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the AttendanceStatus class.
 * @author sebok
 */
public class AttendanceStatusTest
{
    
    public AttendanceStatusTest()
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
     * Test of isTeacherSet method, of class AttendanceStatus.
     */
    @Test
    public void testIsTeacherSet()
    {
        System.out.println("isTeacherSet");
        AttendanceStatus instance = new AttendanceStatus();
        boolean expResult = false;
        boolean result = instance.isTeacherSet();
        assertEquals(expResult, result);
        expResult = true;
        instance.setTeacherSet(true);
        result = instance.isTeacherSet();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTeacherSet method, of class AttendanceStatus.
     */
    @Test
    public void testSetTeacherSet()
    {
        System.out.println("setTeacherSet");
        boolean teacherSet = false;
        AttendanceStatus instance = new AttendanceStatus();
        instance.setTeacherSet(teacherSet);
        assertEquals(instance.isTeacherSet(), teacherSet);
        teacherSet = true;
        instance.setTeacherSet(teacherSet);
        assertEquals(instance.isTeacherSet(), teacherSet);
    }

    /**
     * Test of getDate method, of class AttendanceStatus.
     */
    @Test
    public void testGetDate()
    {
        System.out.println("getDate");
        LocalDate date = LocalDate.of(2018, 04, 5);
        AttendanceStatus instance = new AttendanceStatus(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("w, yyyy-MM-dd");
        String expResult = "Week " + date.format(formatter);
        String result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateAsLocalDate method, of class AttendanceStatus.
     */
    @Test
    public void testGetDateAsLocalDate()
    {
        System.out.println("getDateAsLocalDate");
        LocalDate date = LocalDate.of(2018, 04, 05);
        AttendanceStatus instance = new AttendanceStatus(date);
        LocalDate expResult = date;
        LocalDate result = instance.getDateAsLocalDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class AttendanceStatus.
     */
    @Test
    public void testSetStatus()
    {
        System.out.println("setStatus");
        int status = 0;
        AttendanceStatus instance = new AttendanceStatus();
        instance.setStatus(status);
        assertEquals(status, instance.getStatusAsNumber());
        status = 1;
        instance.setStatus(status);
        assertEquals(status, instance.getStatusAsNumber());
        status = -1;
        instance.setStatus(status);
        assertEquals(status, instance.getStatusAsNumber());
    }

    /**
     * Test of getStatusAsNumber method, of class AttendanceStatus.
     */
    @Test
    public void testGetStatusAsNumber()
    {
        System.out.println("getStatusAsNumber");
        int status = 0;
        AttendanceStatus instance = new AttendanceStatus();
        instance.setStatus(status);
        assertEquals(status, instance.getStatusAsNumber());
        status = 1;
        instance.setStatus(status);
        assertEquals(status, instance.getStatusAsNumber());
        status = -1;
        instance.setStatus(status);
        assertEquals(status, instance.getStatusAsNumber());
    }

    /**
     * Test of getStatus method, of class AttendanceStatus.
     */
    @Test
    public void testGetStatus()
    {
        System.out.println("getStatus");
        AttendanceStatus instance = new AttendanceStatus();
        instance.setStatus(0);
        
        String expResult = "🗙";
        String result = instance.getStatus();
        assertEquals(expResult, result);
        instance.setStatus(1);
        
        expResult = "✔";
        result = instance.getStatus();
        assertEquals(expResult, result);
        instance.setStatus(-1);
        
        expResult = "-";
        result = instance.getStatus();
        assertEquals(expResult, result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatusAsBoolean method, of class AttendanceStatus.
     */
    @Test
    public void testGetStatusAsBoolean()
    {
        System.out.println("getStatusAsBoolean");
        AttendanceStatus instance = new AttendanceStatus();
        instance.setStatus(1);
        Boolean result = instance.getStatusAsBoolean();
        assert(result);
        instance.setStatus(0);
        result = instance.getStatusAsBoolean();
        assert(result == false);
    }

    /**
     * Test of getId method, of class AttendanceStatus.
     */
    @Test
    public void testGetId()
    {
        System.out.println("getId");
        AttendanceStatus instance = new AttendanceStatus();
        instance.setId(0);
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);

        instance.setId(1);
        expResult = 1;
        result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class AttendanceStatus.
     */
    @Test
    public void testSetId()
    {
        System.out.println("setId");
        AttendanceStatus instance = new AttendanceStatus();
        instance.setId(0);
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);

        instance.setId(1);
        expResult = 1;
        result = instance.getId();
        assertEquals(expResult, result);
    }
    
}
