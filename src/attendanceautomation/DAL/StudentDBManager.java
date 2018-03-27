package attendanceautomation.DAL;

import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bence
 */
public class StudentDBManager {

    private ConnectionManager cm = ConnectionManager.getInstance();

    public List<Student> getStudentFromDB() throws DALException {
        List<Student> students = new ArrayList();
        
        InputStream inputStream;
        
        try (Connection con = cm.getConnection()) {
            //PreparedStatement ps = con.prepareStatement("SELECT id, name, email, image FROM Student");
            PreparedStatement ps = con.prepareStatement("SELECT id, name, email FROM Student");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student temp = new Student();
                temp.setEmail(rs.getString("email"));
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));
//                inputStream = rs.getBinaryStream("image");
//                File target = new File(temp.getName()+".png");
//                java.nio.file.Files.copy(inputStream, target.toPath());
//                temp.setImageFile(target);
                students.add(temp);
            }

        }
        catch (Exception ex) {
            throw new DALException(ex);
        }
        return students;
    }

    /**
     * Save message to DB
     *
     * @param msg The message that will be saved to the database.
     */
    public void saveMessage(StudentMessage msg) throws DALException {
        try (Connection con = cm.getConnection()) {
            String sql = "INSERT INTO StudentMessage(teacherId, studentId, historyId, message, status, seen) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, msg.getTeacherId());
            ps.setInt(2, msg.getStudentId());
            ps.setInt(3, msg.getAttendanceHistoryId());
            ps.setString(4, msg.getMessage());
            ps.setBoolean(5, msg.getStatus());
            ps.setBoolean(6, msg.hasBeenSeen());
            int affected = ps.executeUpdate();
            if (affected < 1) {
                throw new DALException("Message could not be saved!");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                msg.setId(rs.getInt(1));
            }
        }
        catch (Exception ex) {
            throw new DALException(ex);
        }
    }

    /**
     * Save an image of a Student to the database.
     * @param p The person whose image will be saved.
     */
    void saveImage(Student s) throws DALException
    {        
        try (Connection con = cm.getConnection())
        {
            FileInputStream f = new FileInputStream(s.getImageFile());
            
            String sql = "INSERT INTO Student (image) VALUES (?);";
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
