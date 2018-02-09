package attendanceautomation.BLL;

import attendanceautomation.DAL.DALManager;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Teacher;
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
    
    /**
     * Retrieve a class based on an id.
     * @param id The id of the class.
     * @return A class with the corresponding id.
     */
    public ClassData getClassData(int id)
    {
        return dManager.getClassData(id);
    }
    
    /**
     * Retrieve a teacher based on an id.
     * @param id The id of the teacher.
     * @return A teacher with the corresponding id.
     */
    public Teacher getTeacher(int id)
    {
        return dManager.getTeacher(id);
    }
    
    
}
