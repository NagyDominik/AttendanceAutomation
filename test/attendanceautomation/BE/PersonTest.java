package attendanceautomation.BE;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Person class.
 * Does not test the methods for setting/getting images and image files.
 * @author sebok
 */
public class PersonTest
{
    
    public PersonTest()
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
     * Test of getName method, of class Person.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Person instance = new PersonImpl();
        String expResult = "Test Name";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Person.
     */
    @Test
    public void testSetName()
    {
        System.out.println("setName");
        Person instance = new PersonImpl();
        String expResult = "Test Name";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Person.
     */
    @Test
    public void testGetId()
    {
        System.out.println("getId");
        Person instance = new PersonImpl();
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
     * Test of setId method, of class Person.
     */
    @Test
    public void testSetId()
    {
        System.out.println("setId");
        Person instance = new PersonImpl();
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
     * Test of getEmail method, of class Person.
     */
    @Test
    public void testGetEmail()
    {
        System.out.println("getEmail");
        Person instance = new PersonImpl();
        String expResult = "test@email.com";
        instance.setEmail(expResult);
        String result = instance.getEmail();
        assertEquals(expResult, result);

        expResult = "test2@email.com";
        instance.setEmail(expResult);
        result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Person.
     */
    @Test
    public void testSetEmail()
    {
        System.out.println("setEmail");
        Person instance = new PersonImpl();
        String expResult = "test@email.com";
        instance.setEmail(expResult);
        String result = instance.getEmail();
        assertEquals(expResult, result);

        expResult = "test2@email.com";
        instance.setEmail(expResult);
        result = instance.getEmail();
        assertEquals(expResult, result);
    }

    public class PersonImpl extends Person
    {
    }
    
}
