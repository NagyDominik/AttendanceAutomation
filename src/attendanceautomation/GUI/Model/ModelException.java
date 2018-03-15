package attendanceautomation.GUI.Model;

/**
 * Represent an error that occurred in the Model.
 * @author sebok
 */
public class ModelException extends Exception
{

    public ModelException(Exception ex)
    {
        super(ex.getMessage());
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
    
    
}
