/**
 * Project 3 CallCenter class, it implements (for a simulation)
 * a call center, keeping track of incoming calls and answering
 * calls at the front of the queue when it their time.
 * 
 * @author Dylan Wilson
 * @version Mar 09, 2025
 */
public class CallCenter
{
    /**
     * Instance variables for this class.
     * Define an array for the call queue,
     * and instance variable for the call
     * that is on the line, and counters
     * for the clock (time) and for the
     * number of elements in the queue.
     */
    private Call[] callQueue;
    private Call onTheLine;
    private int clock;
    private int numCalls;
    /**
     * Default constructor for the CallCenter, initializes.
     * all instance variables with default values.
     */
    public CallCenter()
    {
        this.callQueue = new Call[10];
        this.onTheLine = null;
        this.clock = 0;
        this.numCalls = 0;
    }

    /**
     * Adds a call to the call queue.
     * 
     * @param name of the caller.
     * @param vip status of the caller.
     * @param duration of this call.
     * @return true if the call is added to the call queue, 
     * false otherwise.
     */
    public boolean addCall(String name, boolean vip, int duration) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Caller name cannot be null or empty.");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Call duration must be positive.");
        }
        if (numCalls >= callQueue.length) {
            return false; 
        }
    
        Call newCall = new Call(name, vip, duration);
    
        if (vip) { 
            for (int i = numCalls; i > 0; i--) {
                callQueue[i] = callQueue[i - 1];
            }
            callQueue[0] = newCall;
        } else {
            callQueue[numCalls] = newCall;
        }
    
        numCalls++;
        return true;
    } 

    /**
     * Simulates answering the call from the call.
     * in the front of the queue.
     * @return null if an error is found, or the call 
     * object if answered.
     */
    public Call answerCall()
    {
        if (onTheLine != null || numCalls == 0) 
        {
            return null;
        }

        onTheLine = callQueue[0];
        for (int i = 0; i < numCalls - 1; i++) 
        {
            callQueue[i] = callQueue[i + 1];
        }

        callQueue[numCalls - 1] = null;
        numCalls--;
        return onTheLine;
    }

    /**
     * The call center has a max capacity, at which point it
     * cannot take new calls until it answers and hangs up on
     * another call. This returns true if we are at capacity.
     * @return true if call center is full
     */
    public boolean isCallCenterAtCapacity()
    {
        return numCalls == 10;
    }

    /**
     * Returns the number of calls waiting to be answered.
     * @return number of calls in queue
     */
    public int numCallsWaiting()
    {
        return numCalls;
    }

    /**
     * Given a caller name, this returns the position in 
     * the queue for a caller. If there is no call with 
     * that name, then it returns -1. Worth noting that 
     * the call at the front of the queue is at position
     * 1 (but internally it is at index 0)
     * @param n name of the caller to search for
     * @return position (1..n) or -1 if not found
     */
    public int positionInLine(String n)
    {
        for (int i = 0; i < numCalls; i++)
        {
            if (callQueue[i].getName().equals(n))
            {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Is the call center busy, i.e., are they on a call
     * right now?
     * @return true if call center is on a call
     */
    public boolean isBusy()
    {
        return onTheLine != null;   
    }

    /**
     * Returns the call object that is current on the call 
     * with the call center. If there is no current call, 
     * returns null.
     * @return call object for the current call.
     */
    public Call onTheLine()
    {
        return onTheLine;
    }

    /**
     * Simulates time passing. The clock ticks and decrements
     * the time left on the call for a call that is online.
     */
    public void clockTick()
    {
        clock++;
        if (onTheLine != null)
        {
            onTheLine.decrement();
            if (onTheLine.getDuration() == 0)
            {
                onTheLine = null;
            }
        }
    }

    /**
     * Returns the current time in the simulation.
     * @return value of the clock
     */
    public int getClock()
    {
        return clock;
    }
}
