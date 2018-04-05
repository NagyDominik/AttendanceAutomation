package attendanceautomation.BE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the ClassData class.
 * @author sebok
 */
public class ClassDataTest
{
    
    public ClassDataTest()
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
     * Test of setId method, of class ClassData.
     */
    @Test
    public void testSetId()
    {
        System.out.println("setId");
        int id = 0;
        ClassData instance = new ClassData();
        instance.setId(id);
        assertEquals(id, instance.getId());
        id = 1;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of setParticipants method, of class ClassData.
     */
    @Test
    public void testSetParticipants()
    {
        System.out.println("setParticipants");
        ObservableList<Student> participants = FXCollections.observableArrayList();
        participants.add(new Student());
        participants.add(new Student());
        ClassData instance = new ClassData();
        instance.setParticipants(participants);
        assertEquals(participants, instance.getParticipants());
    }

    /**
     * Test of setClassName method, of class ClassData.
     */
    @Test
    public void testSetClassName()
    {
        System.out.println("setClassName");
        String className = "CS_2018_TESTCLASS";
        ClassData instance = new ClassData();
        instance.setClassName(className);
        assertEquals(className, instance.getClassName());
        className = "CS_2018_NEWTESTCLASS";
        instance.setClassName(className);
        assertEquals(className, instance.getClassName());
    }

    /**
     * Test of setTeacher method, of class ClassData.
     */
    @Test
    public void testSetTeacher()
    {
        System.out.println("setTeacher");
        Teacher teacher = new Teacher();
        ClassData instance = new ClassData();
        instance.setTeacher(teacher);
        assertEquals(teacher, instance.getTeacher());
        teacher = new Teacher();
        instance.setTeacher(teacher);
        assertEquals(teacher, instance.getTeacher());
    }

    /**
     * Test of getTeacher method, of class ClassData.
     */
    @Test
    public void testGetTeacher()
    {
        System.out.println("getTeacher");
        Teacher teacher = new Teacher();
        ClassData instance = new ClassData();
        instance.setTeacher(teacher);
        assertEquals(teacher, instance.getTeacher());
        teacher = new Teacher();
        instance.setTeacher(teacher);
        assertEquals(teacher, instance.getTeacher());
    }

    /**
     * Test of getParticipants method, of class ClassData.
     */
    @Test
    public void testGetParticipants()
    {
        System.out.println("getParticipants");
        ClassData instance = new ClassData();
        ObservableList<Student> expResult = FXCollections.observableArrayList();
        expResult.add(new Student());
        expResult.add(new Student());
        instance.setParticipants(expResult);
        ObservableList<Student> result = instance.getParticipants();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClassName method, of class ClassData.
     */
    @Test
    public void testGetClassName()
    {
        System.out.println("getClassName");
        ClassData instance = new ClassData();
        String expResult = "CS_2018_Test";
        instance.setClassName(expResult);
        String result = instance.getClassName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class ClassData.
     */
    @Test
    public void testGetId()
    {
        System.out.println("getId");
        ClassData instance = new ClassData();
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
     * Test of addStudent method, of class ClassData.
     */
    @Test
    public void testAddStudent()
    {
        System.out.println("addStudent");
        Student student = new Student();
        ClassData instance = new ClassData();
        instance.addStudent(student);
        assertEquals(student, instance.getParticipants().get(0));
        
        student = new Student();
        instance.addStudent(student);
        assertEquals(student, instance.getParticipants().get(1));
    }

    /**
     * Test of toString method, of class ClassData.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        ClassData instance = new ClassData();
        String expResult = "CS_2018_Test";
        instance.setClassName(expResult);
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
