package attendanceautomation.GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Utility class, used to show alerts on the GUI.
 * @author sebok
 */
public class AlertWindow
{
    public static void showAlert(Exception ex)
    {
        Alert a = new Alert(Alert.AlertType.WARNING, ex.getMessage(), ButtonType.OK);
        a.show();
    }
}
