package attendanceautomation.BE;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the StudentMessage class.
 * @author sebok
 */
public class StudentMessageTest
{
    
    public StudentMessageTest()
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
     * Test of hasBeenSeen method, of class StudentMessage.
     */
    @Test
    public void testHasBeenSeen()
    {
        System.out.println("hasBeenSeen");
        StudentMessage instance = new StudentMessage();
        boolean expResult = false;
        boolean result = instance.hasBeenSeen();
        assertEquals(expResult, result);
        instance.setHasBeenSeen(true);
        expResult = true;
        result = instance.hasBeenSeen();
        assertEquals(expResult, result);
        instance.setHasBeenSeen(false);
        expResult = false;
        result = instance.hasBeenSeen();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHasBeenSeen method, of class StudentMessage, the same as hasBeenSeen.
     */
    @Test
    public void testSetHasBeenSeen()
    {
        System.out.println("setHasBeenSeen");
        StudentMessage instance = new StudentMessage();
        boolean expResult = false;
        boolean result = instance.hasBeenSeen();
        assertEquals(expResult, result);
        instance.setHasBeenSeen(true);
        expResult = true;
        result = instance.hasBeenSeen();
        assertEquals(expResult, result);
        instance.setHasBeenSeen(false);
        expResult = false;
        result = instance.hasBeenSeen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class StudentMessage.
     */
    @Test
    public void testGetId()
    {
        System.out.println("getId");
        StudentMessage instance = new StudentMessage();
        int expResult = 0;
        instance.setId(expResult);
        int result = instance.getId();
        assertEquals(expResult, result);
        expResult = 1;
        instance.setId(expResult);
        result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class StudentMessage, the same as setId.
     */
    @Test
    public void testSetId()
    {
        System.out.println("setId");
        StudentMessage instance = new StudentMessage();
        int expResult = 0;
        instance.setId(expResult);
        int result = instance.getId();
        assertEquals(expResult, result);
        expResult = 1;
        instance.setId(expResult);
        result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatus method, of class StudentMessage.
     */
    @Test
    public void testGetStatus()
    {
        System.out.println("getStatus");
        StudentMessage instance = new StudentMessage();
        Boolean expResult = false;
        instance.setStatus(expResult);
        Boolean result = instance.getStatus();
        assertEquals(expResult, result);
        expResult = true;
        instance.setStatus(expResult);
        result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class StudentMessage, same as getStatus;
     */
    @Test
    public void testSetStatus()
    {
        System.out.println("setStatus");
        StudentMessage instance = new StudentMessage();
        Boolean expResult = false;
        instance.setStatus(expResult);
        Boolean result = instance.getStatus();
        assertEquals(expResult, result);
        expResult = true;
        instance.setStatus(expResult);
        result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessage method, of class StudentMessage.
     */
    @Test
    public void testGetMessage()
    {
        System.out.println("getMessage");
        StudentMessage instance = new StudentMessage();
        String expResult = "Test message";
        instance.setMessage(expResult);
        String result = instance.getMessage();
        assertEquals(expResult, result);
        expResult = "Test message 2";
        instance.setMessage(expResult);
        result = instance.getMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessage method, of class StudentMessage, same as getMessage.
     */
    @Test
    public void testSetMessage()
    {
        System.out.println("setMessage");
        StudentMessage instance = new StudentMessage();
        String expResult = "Test message";
        instance.setMessage(expResult);
        String result = instance.getMessage();
        assertEquals(expResult, result);
        expResult = "Test message 2";
        instance.setMessage(expResult);
        result = instance.getMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTeacherId method, of class StudentMessage.
     */
    @Test
    public void testGetTeacherId()
    {
        System.out.println("getTeacherId");
        StudentMessage instance = new StudentMessage(1, 2, Boolean.TRUE, "Test", 0);
        int expResult = 1;
        int result = instance.getTeacherId();
        assertEquals(expResult, result);
        instance = new StudentMessage(42, 4, Boolean.TRUE, "Test msg", 3);
        
        expResult = 42;
        result = instance.getTeacherId();
        assertEquals(expResult, result);
    }
    

    /**
     * Test of getStudentId method, of class StudentMessage.
     */
    @Test
    public void testGetStudentId()
    {
        System.out.println("getStudentId");
        StudentMessage instance = new StudentMessage(1, 2, Boolean.TRUE, "Test", 0);
        int expResult = 2;
        int result = instance.getStudentId();
        assertEquals(expResult, result);
        instance = new StudentMessage(42, 87, Boolean.TRUE, "Test msg", 3);
        
        expResult = 87;
        result = instance.getStudentId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAttendanceHistoryId method, of class StudentMessage.
     */
    @Test
    public void testGetAttendanceHistoryId()
    {
        System.out.println("getAttendanceHistoryId");
        StudentMessage instance = new StudentMessage(1, 2, Boolean.TRUE, "Test", 32);
        int expResult = 32;
        int result = instance.getAttendanceHistoryId();
        assertEquals(expResult, result);
        instance = new StudentMessage(42, 87, Boolean.TRUE, "Test msg", 43);
        
        expResult = 43;
        result = instance.getAttendanceHistoryId();
        assertEquals(expResult, result);
    }
    
}
