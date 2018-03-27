package attendanceautomation.BE;

import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author Dominik
 */
public abstract class Person {

    private String name;
    private File imageFile;
    private Image image;
    
    public String getName() {
        return name;
    }
    
    public abstract int getId();

    public File getImage()
    {
        return imageFile;
    }

    public void setImage(File image)
    {
        this.imageFile = image;
        this.image = new Image(image.getAbsolutePath());
    }
}
