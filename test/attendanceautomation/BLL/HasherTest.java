package attendanceautomation.BLL;

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
public class HasherTest {

    public HasherTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of hash method, of class Hasher.
     */
    @Test
    public void testHash() {
        System.out.println("Test hashing method");
        String password1 = "password";
        String password2 = "12345";
        String password3 = "qwerty";

        Hasher instance = new Hasher();
        String expResult1 = "5e884898da2847151d0e56f8dc6292773603dd6aabbdd62a11ef721d1542d8";
        String expResult2 = "5994471abb1112afcc18159f6cc74b4f511b9986da59b3caf5a9c173cacfc5";
        String expResult3 = "65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5";

        String result1 = instance.hash(password1);
        String result2 = instance.hash(password2);
        String result3 = instance.hash(password3);

        System.out.println("Result: " + result3);

        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
    }

}
