package attendanceautomation.BLL;

import attendanceautomation.DAL.DALManager;

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
