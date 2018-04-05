package attendanceautomation.BE;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Teacher class.
 * @author sebok
 */
public class TeacherTest
{
    
    public TeacherTest()
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
     * Test of setClasses method, of class Teacher.
     */
    @Test
    public void testSetClasses()
    {
        System.out.println("setClasses");
        ArrayList<ClassData> classes = new ArrayList<>();
        classes.add(new ClassData());
        classes.add(new ClassData());
        Teacher instance = new Teacher();
        instance.setClasses(classes);
        assertEquals(classes, instance.getClasses());
        classes.add(new ClassData());
        instance.setClasses(classes);
        assertEquals(classes, instance.getClasses());
    }

    /**
     * Test of getClasses method, of class Teacher, the same as setClasses.
     */
    @Test
    public void testGetClasses()
    {
        System.out.println("getClasses");
        ArrayList<ClassData> classes = new ArrayList<>();
        classes.add(new ClassData());
        classes.add(new ClassData());
        Teacher instance = new Teacher();
        instance.setClasses(classes);
        assertEquals(classes, instance.getClasses());
        classes.add(new ClassData());
        instance.setClasses(classes);
        assertEquals(classes, instance.getClasses());
    }
    
}
