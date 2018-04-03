package attendanceautomation.DAL;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
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

    /**
     * Retrieve the list of students from the database.
     * @return The list of students.
     * @throws DALException If something goes wrong during database access.
     */
    public List<Student> getStudentFromDB() throws DALException {
        List<Student> students = new ArrayList<>();
        InputStream inputStream;
        
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, name, email, image, classid FROM Student");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student temp = new Student();
                temp.setEmail(rs.getString("email"));
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));
                temp.setClassID(rs.getInt("classid"));
                inputStream = rs.getBinaryStream("image");
                if (inputStream != null) {
                    File target = new File("src/img/students/" + temp.getName().replaceAll(" ", "") + ".png");
                    target.mkdirs();
                    java.nio.file.Files.copy(inputStream, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    temp.setImageFile(target);
                } else {
                    File dir = new File("src/img");
                    dir.mkdirs();
                    File imageNotFound = new File(dir, "help.png");
                    temp.setImageFile(imageNotFound);
                }

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
        } catch (Exception ex) {
            throw new DALException(ex);
        }
    }

    /**
     * Save an image of a Student to the database.
     *
     * @param s The person whose image will be saved.
     */
    public void saveImage(Student s) throws DALException {
        try (Connection con = cm.getConnection()) {
            FileInputStream f = new FileInputStream(s.getImageFile());

            String sql = "UPDATE Student SET image = ? WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBinaryStream(1, (InputStream) f);
            ps.setInt(2, s.getId());
            int affected = ps.executeUpdate();
            if (affected < 1) {
                throw new DALException("Could not save image to database.");
            }
        } catch (SQLException ex) {
            throw new DALException(ex);
        } catch (FileNotFoundException ex) {
            throw new DALException("Image file not found! " + ex.getMessage());
        }
    }

    public void changePassword(int userId, String hash) throws DALException {
        try (Connection con = cm.getConnection()) {
            String sql = "UPDATE Student SET password = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hash);
            ps.setInt(2, userId);
            int affected = ps.executeUpdate();
            if (affected < 1) {
                throw new DALException("Unable to change password!");
            }
        } catch (SQLException ex) {
            throw new DALException(ex);
        }
    }

    /**
     * Save a status of a student.
     * @param status
     * @param student 
     */
    public void saveStatus(AttendanceStatus status, Student student) throws DALException
    {
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO History(id, date, status, studentid, teacherset) VALUES(?, ?, ?, ?, ?)");
            ps.setInt(1, status.getId());
            ps.setString(2, status.getDate());
            ps.setInt(3, status.getStatusAsNumber());
            ps.setInt(4, student.getId());
            ps.setBoolean(5, status.isTeacherSet());
            ps.executeUpdate();
        }
        catch (Exception ex) {
            throw new DALException(ex);
        }
    }
}
