package attendanceautomation.DAL;

/**
 * Represent an error that occurred in the DAL
 * @author sebok
 */
public class DALException extends Exception
{

    public DALException(String message)
    {
        super(message);
    }
    
    public DALException(Exception ex)
    {
        super(ex.getMessage());
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
    
    
}
