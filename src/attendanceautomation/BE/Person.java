package attendanceautomation.BE;

import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author Dominik
 */
public abstract class Person {

    private int id;
    private String name;
    private File imageFile;
    private Image image;
    private String email;

    public Person()
    {
    }
    
    public Person(int id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    public File getImageFile()
    {
        return imageFile;
    }

    public void setImageFile(File image)
    {
        this.imageFile = image;
        this.image = new Image("file:" + image.getAbsolutePath());
    }
    
    public Image getImage()
    {
        return this.image;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
