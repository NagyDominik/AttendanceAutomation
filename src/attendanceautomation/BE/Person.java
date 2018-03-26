/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BE;

import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author Dominik
 */
public abstract class Person {

    private String name;
    private File image;
    
    public String getName() {
        return name;
    }
    
    public abstract int getId();

    public File getImage()
    {
        return image;
    }

    public void setImage(File image)
    {
        this.image = image;
    }
}
