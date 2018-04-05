package attendanceautomation.DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Dominik
 */
public class LocalDataManager {

    public void saveData(String email, String password) throws DALException {
        try {
            String loginFile = "login.bin";
            ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream(loginFile));
            objectOS.writeUTF(email);
            objectOS.writeUTF(password);
            objectOS.close();
        }
        catch (IOException ex) {
            throw new DALException(ex);
        }
    }

    public String[] getLocalData() throws DALException {
        String[] data = new String[2];
        if (checkFileExists()) {
            try {
                String loginFile = "login.bin";
                ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream(loginFile));
                data[0] = objectIS.readUTF();
                data[1] = objectIS.readUTF();
            }
            catch (IOException ex) {
                throw new DALException(ex);
            }
        }
        return data;
    }

    private boolean checkFileExists() {
        File f = new File("login.bin");
        return f.exists();
    }

    void clearData() throws DALException {
        try {
            Files.deleteIfExists(Paths.get("login.bin"));
        }
        catch (IOException ex) {
            throw new DALException(ex);
        }
    }

}
