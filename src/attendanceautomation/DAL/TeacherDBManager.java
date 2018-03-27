package attendanceautomation.DAL;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.BE.Teacher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bence
 */
public class TeacherDBManager {

    private ConnectionManager cm = ConnectionManager.getInstance();

    public List<Teacher> getTeacherFromDB() throws DALException {
        List<Teacher> teachers = new ArrayList<>();
        
        InputStream inputStream = null;

        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT email, id, name, image FROM Teacher");
            ResultSet rs =ps.executeQuery();
            while (rs.next()) {
                Teacher temp = new Teacher();
                temp.setEmail(rs.getString("email"));
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));
                inputStream = rs.getBinaryStream("image");
                File target = new File(temp.getName()+".png");
                java.nio.file.Files.copy(inputStream, target.toPath());
                temp.setImage(target);
            }
        }
        catch (Exception ex) {
            throw new DALException(ex);
        }
        return teachers;
    }

    /**
     * Load the messages
     *
     * @return The list of messages
     */
    public List<StudentMessage> getStudentMessages(int id) throws DALException {
        List<StudentMessage> messages = new ArrayList<>();
        try (Connection con = cm.getConnection()) {
            String sql = "SELECT * FROM StudentMessage";//WHERE teacherid = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            // ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentMessage msg = new StudentMessage(rs.getInt("teacherId"), rs.getInt("studentId"), rs.getBoolean("status"), rs.getString("message"), rs.getInt("historyId"));
                msg.setHasBeenSeen(rs.getBoolean("seen"));
                msg.setId(rs.getInt("id"));
                messages.add(msg);
            }
        }
        catch (SQLException ex) {
            throw new DALException(ex);
        }
        return messages;
    }

    /**
     * Update an existing StudentMessage object in the database.
     *
     * @param msg The message that will be updated.
     * @throws DALException If something goes wrong during database operations.
     */
    public void updateMessage(StudentMessage msg) throws DALException {
        try (Connection con = cm.getConnection()) {
            String sql = "UPDATE StudentMessage SET teacherId = ?, studentId = ?, historyId = ?, message = ?, status = ?, seen = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, msg.getTeacherId());
            ps.setInt(2, msg.getStudentId());
            ps.setInt(3, msg.getAttendanceHistoryId());
            ps.setString(4, msg.getMessage());
            ps.setBoolean(5, msg.getStatus());
            ps.setBoolean(6, msg.hasBeenSeen());
            ps.setInt(7, msg.getId());
            int affected = ps.executeUpdate();
            if (affected < 0) {
                throw new DALException("Message could not be edited!");
            }
        }
        catch (SQLException ex) {
            throw new DALException(ex);
        }
    }

    /**
     * Checks if there are unread messages of teacher with the given id
     *
     * @param id The id of the teacher.
     * @return True if there are unread messages, false otherwise.
     * @throws DALException If something goes wrong during database operations.
     */
    public boolean hasUnreadMessages(int id) throws DALException {
        try (Connection con = cm.getConnection()) {
            String sql = "SELECT [teacherid], COUNT([seen]) as SeenCount FROM [CS2017B_7_AttendanceAutomation].[dbo].[StudentMessage] WHERE [seen] = 0 GROUP BY teacherid;"; //AND [teacherId] = ? GROUP BY teacherid;"; //" AND teacherId = id"
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        }
        catch (SQLException ex) {
            throw new DALException(ex);
        }
    }

    /**
     * Update an existing AttendanceStatus in the database
     *
     * @param attendatnceStatus The attendance status that will be updated
     */
    public void updateAttendanceStatus(AttendanceStatus attendatnceStatus) throws DALException {
        Date d = Date.valueOf(attendatnceStatus.getDateAsLocalDate());
        try (Connection con = cm.getConnection()) {
            String sql = "UPDATE History SET date = ?, status = ?, teacherset = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, d);
            ps.setInt(2, attendatnceStatus.getStatusAsNumber());
            ps.setBoolean(3, attendatnceStatus.isTeacherSet());
            ps.setInt(4, attendatnceStatus.getId());
            int affected = ps.executeUpdate();
            if (affected < 1) {
                throw new DALException("Update unsuccessfull!");
            }
        }
        catch (SQLException ex) {
            throw new DALException(ex);
        }
    }

     /**
     * Save an image of a Teacher to the database.
     * @param p The person whose image will be saved.
     */
    void saveImage(Teacher t) throws DALException
    {
        try (Connection con = cm.getConnection())
        {
            FileInputStream f = new FileInputStream(t.getImageFile());
            
            String sql = "INSERT INTO Teacher (image) VALUES (?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBinaryStream(1, (InputStream)f);
            int affected = ps.executeUpdate();
            if (affected < 1)
            {
                throw new DALException("Could not save image to database.");
            }
        }
        catch (SQLException ex)
        {
            throw new DALException(ex);
        } catch (FileNotFoundException ex)
        {
            throw new DALException("Image file not found! " + ex.getMessage()); 
        }
    }
}
