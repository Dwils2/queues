import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 * Testing of the call center class.
 * This will allow us to test the inner methods of the call center class.
 * 
 * @author  Dylan Wilson
 * @version Mar 20, 2025
 */
public class CallCenterTest {
    private CallCenter runner;

    /**
     * setup() method, runs before each of your test methods.
     * Use this method to recreate the objects needed for
     * testing your class.
     */
    @Before
    public void setUp() {
        runner = new CallCenter();
    }
    
    /**
     * Testing of the addCall method.
     */
    @Test
    public void testAddCall() {
        assertTrue(runner.addCall("Alice", false, 5));
        assertEquals(1, runner.numCallsWaiting());
    }

    /**
     * Testing of the addCall method with a VIP call.
     */
    @Test
    public void testAddCallVipSuccess() {
        runner.addCall("Bob", false, 4); // Regular call
        runner.addCall("Charlie", false, 3); // Another regular call
        assertTrue(runner.addCall("VIP", true, 6)); // VIP call at front
        assertEquals(1, runner.positionInLine("VIP"));
    }

    /**
     * Testing of the addCall method with a null name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddCallNullName() {
        runner.addCall(null, false, 3);
        fail("Should have thrown an exception.");
    }

    /**
     * Testing of the addCall method with an empty name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddCallEmptyName() {
        runner.addCall("", false, 3);
        fail("Should have thrown an exception.");
    }

    /**
     * Testing of the addCall method with a negative duration.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddCallNegativeDuration() {
        runner.addCall("Alice", false, -2);
        fail("An exception should have been thrown.");
    }

    /**
     * Testing of the addCall method with a full queue.
     */
    @Test
    public void testAddCallFullQueue() {
        for (int i = 0; i < 10; i++) {
            runner.addCall("Caller" + i, false, 5);
        }
        assertFalse(runner.addCall("Alice", false, 5));
        assertEquals(10, runner.numCallsWaiting());
    }
    
    /**
     * Testing of the answerCall method.
     */
    @Test
    public void testAnswerCall() {
        runner.addCall("Alice", false, 5);
        Call answered = runner.answerCall();
        assertNotNull(answered);
        assertEquals("Alice", answered.getName());
        assertEquals(0, runner.numCallsWaiting());
    }
    
    /**
     * Testing of the answerCall method with no call.
     */
    @Test
    public void testAnswerCallNoCall() {
        CallCenter center = new CallCenter();
        Call answered = center.answerCall();
        assertEquals(null, answered);
    }

    /**
     * Testing of the answerCall method with a VIP call.
     */
    @Test
    public void testAnswerCallVIP() {
        runner.addCall("Alice", false, 5);
        runner.addCall("Bob", true, 3);
        Call answered = runner.answerCall();
        assertNotNull(answered);
        assertEquals("Bob", answered.getName());
    }

    /**
     * Testing of the answerCall method with a call in progress.
     */
    @Test
    public void testAnswerCallInProgress() {
        runner.addCall("Alice", false, 5);
        runner.answerCall();
        Call answered = runner.answerCall();
        assertEquals(null, answered);
    }

    /**
     * Testing of the capacity method.
     */
    @Test
    public void testIsCallCenterAtCapacity() {
        for (int i = 0; i < 10; i++) {
            runner.addCall("Caller" + i, false, 5);
        }
        assertTrue(runner.isCallCenterAtCapacity());
    }

    /**
     * Testing of the capacity methond not at capacity.
     */
    @Test
    public void testIsCallCenterAtCapacityNotFull() {
        assertFalse(runner.isCallCenterAtCapacity());
    }
    /**
     * Testing of the positionInLine method.
     */
    @Test
    public void testPositionInLine() {
        runner.addCall("Alice", false, 5);
        runner.addCall("Bob", false, 3);
        assertEquals(1, runner.positionInLine("Alice"));
        assertEquals(2, runner.positionInLine("Bob"));
        assertEquals(-1, runner.positionInLine("Charlie"));
    }
    
    /**
     * Testing of the isBusy method.
     */
    @Test
    public void testIsBusy() {
        assertFalse(runner.isBusy());
        runner.addCall("Alice", false, 5);
        runner.answerCall();
        assertTrue(runner.isBusy());
    }
    /**
     * Testing of the onTheLine method.
     */
    @Test
    public void testOnTheLine() {
        runner.addCall("Alice", false, 5);
        Call answered = runner.answerCall();
        assertEquals(answered, runner.onTheLine());
    }
    
    /**
     * Testing of the ClockTick method.
     */
    @Test
    public void testClockTick() {
        runner.addCall("Alice", false, 2);
        Call answered = runner.answerCall();
        runner.clockTick();
        assertEquals(1, answered.getDuration());
        runner.clockTick();
        assertEquals(0, answered.getDuration());
        assertFalse(runner.isBusy());
    }
    
    /**
     * Testing of the getClock method.
     */
    @Test
    public void testGetClock() {
        assertEquals(0, runner.getClock());
        runner.clockTick();
        assertEquals(1, runner.getClock());
    }
}
