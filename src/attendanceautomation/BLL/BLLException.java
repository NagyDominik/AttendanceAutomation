package attendanceautomation.BLL;

/**
 *Represent an error that occurred in the BLL
 * @author sebok
 */
public class BLLException extends Exception
{

    public BLLException(Exception ex)
    {
        super(ex.getMessage());
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
}
