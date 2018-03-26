/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.DAL;

import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, name, email FROM Student");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student temp = new Student();
                temp.setEmail(rs.getString("email"));
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));
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
}
