/**
 * Project 3 - Call class, used to model a caller
 * into a call center. It stores name of the caller,
 * VIP status and duration of the call.
 * 
 * @author  Dylan Wilson
 */
public class Call 
{
    /**
     * declare instance variables for name, vip, 
     * and duration.
     */
    private String name;
    private boolean vip;
    private int duration;
    /**
     * Describe the default constructor.
     */
    public Call()
    {
        this.name = "Unknown Caller";
        this.vip = false;
        this.duration = 10;
    }

    /**
     * Constructor that initializes all instance variables
     * with values passed as parameters.
     * @param caller name of the caller
     * @param vip boolean true if caller is VIP, false otherwise
     * @param duration length of the call in fake units
     */
    public Call(String caller, boolean vip, int duration)
    {
        this.name = caller;
        this.vip = vip;
        this.duration = duration;
    }
    /**
     * Accessor methods for the instance variable name.
     * @return the value of name.
     */
    public String getName()
    {
        return name;
    }
    /**
     * Accessor methods for the instance variable vip.
     * @return the value of vip.
     */
    public boolean isVIP()
    {
        return vip;
    }
    /**
     * Accessor methods for the instance variable duration.
     * @return the value of duration.
     */
    public int getDuration()
    {
        return duration;
    }
    /**
     * Mutator method for the instance variable duration.
     */
    public void decrement()
    {
        duration--;
    }
}
