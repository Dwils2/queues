import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;
/**
 * Testing of the call class.
 * This will allow us to test the inner methods of the call class.
 * @author  Dylan Wilson
 * @version Mar 27, 2025
 */
public class CallTest {
    private Call runner;
    /**
     * setup() method, runs before each of your test methods.
     * Use this method to recreate the objects needed for
     * testing your class.
     */
    @Before
    public void setUp() {
        runner = new Call();
    }
    /**
     * Testing of the default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        assertEquals("Unknown Caller", runner.getName());
        assertFalse(runner.isVIP());
        assertEquals(10, runner.getDuration());
    }
    /**
     * Testing of the constructor with parameters.
     */
    @Test
    public void testConstructorWithParameters() {
        runner = new Call("Alice", false, 5);
        assertEquals("Alice", runner.getName());
        assertFalse(runner.isVIP());
        assertEquals(5, runner.getDuration());
    }
    /**
     * Testing of the getName method.
     */
    @Test
    public void testGetName() {
        runner = new Call("Alice", false, 5);
        assertEquals("Alice", runner.getName());
    }
    /**
     * Testing of the isVIP method.
     */
    @Test
    public void testIsVIP() {
        runner = new Call("Alice", false, 5);
        assertFalse(runner.isVIP());
    }
    /**
     * Testing of the IsVIP method.
     */
    @Test
    public void testIsVIP2() {
        runner = new Call("Alice", true, 5);
        assertTrue(runner.isVIP());
    }
    /**
     * Testing of the getDuration method.
     */
    @Test
    public void testGetDuration() {
        runner = new Call("Alice", false, 5);
        assertEquals(5, runner.getDuration());
    }
    /**
     * Testing of the decrement method.
     */
    @Test
    public void testDecrement() {
        runner = new Call("Alice", false, 5);
        runner.decrement();
        assertEquals(4, runner.getDuration());
    }
}
