package attendanceautomation.BLL;

import attendanceautomation.DAL.DALManager;

/**
 * Serves as a pass-through layer for now.
 * @author sebok
 */
public class BLLManager
{
    private DALManager dManager;

    public BLLManager()
    {
        dManager = new DALManager();
    }

    public int getData()
    {
        return dManager.getData();
    }
}
