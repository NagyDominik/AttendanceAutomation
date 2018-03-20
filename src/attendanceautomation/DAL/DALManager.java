package attendanceautomation.DAL;

import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.BE.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * COntains mock data (for now)
 *
 * @author sebok
 */
public class DALManager {

    private ConnectionManager cm = ConnectionManager.getInstance();

    /**
     * *******************
     */
    //Mock data
    private List<ClassData> mockClassData;
    private List<Student> mockStudent;
    private List<Teacher> mockTeacher;

    public DALManager() {
        setUpMockData();
    }

    private void setUpMockData() {
        mockTeacher = new ArrayList();
        mockTeacher.add(new Teacher("teacher@easv.dk", "Jeppe Moritz", 2));
        mockTeacher.add(new Teacher("teacher1@easv.dk", "Lars Jorgensen", 1));
        
        mockClassData = new ArrayList();
        mockClassData.add(new ClassData("CS2017A", new Teacher("teacher@easv.dk", "Jeppe Moritz", 2)));
        mockClassData.add(new ClassData("CS2017B" , new Teacher("teacher@easv.dk", "Jeppe Moritz", 2)));

   
        mockStudent = new ArrayList();
        mockStudent.add(new Student("student@easv.dk", "Thomas White", 0));
        mockStudent.add(new Student("student1@easv.dk", "Jesper Boo", 1));
        mockStudent.add(new Student("student2@easv.dk", "Peter Sebok", 2));
        mockStudent.add(new Student("student3@easv.dk", "Dominik Nagy", 3));
        mockStudent.add(new Student("student4@easv.dk", "Daniel McAdams", 4));
        mockStudent.add(new Student("student5@easv.dk", "Bence Matyasi", 5));
                
        mockClassData.get(0).getParticipants().addAll(mockStudent);
        mockClassData.get(1).getParticipants().addAll(mockStudent);
        mockClassData.get(1).getParticipants().remove(2);
    }

    /**
     * Retrieve a class based on their id.
     *
     * @return The class associated with the id.
     */
    public List<ClassData> getMockClassData() {
        return mockClassData;
    }

    /**
     * Retrieve a student based on an id.
     *
     * @return A student with the corresponding id.
     */
    public List<Student> getMockStudent() {
        return mockStudent;
    }

    /**
     *
     * Retrieve a class based on their id.
     *
     * @return The class associated with the id.
     */
    public List<Teacher> getMockTeacher() {
        return mockTeacher;
    }

    /**
     * Attempt login with the specified email and password.
     *
     * @param email The specified email.
     * @param password The specified password.
     * @return A string representing the user type - "Teacher" for teachers,
     * "Student" for students or "None" if there is no match for the email.
     */
    public Person attemptLogin(String email, String password) {
        for (Teacher loginTeacher : mockTeacher) {
            if (loginTeacher.getEmail().equals(email) && password.equals("c596e268fea18473ea763797c0d7f4ef2cc1b13528fa7a8186c96f7da4e81cd")) {
                return loginTeacher;
            }
        }
        for (Student loginStudent : mockStudent) {
            if (loginStudent.getEmail().equals(email) && password.equals("643a5b5e16012e258750c07e363c41568b45165b4fc43874b88c21d99cb55")) {
                return loginStudent;
            }
        }
        return null;
    }

    /**
     * Put a message to the database.
     * @param msg The message that will be saved to the database.
     */
    public void saveMessage(StudentMessage msg) throws DALException
    {
        try(Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO StudentMessage(teacherId, studentId, historyId, message, status, seen) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, msg.getTeacherId());
            ps.setInt(2, msg.getStudentId());
            ps.setInt(3, msg.getAttendanceHistoryId());
            ps.setString(4, msg.getMessage());
            ps.setBoolean(5, msg.getStatus().equals("Present")?true:false);
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
        catch (Exception ex)
        {
            throw new DALException(ex);
        }
    }

    
    /**
     * Load the messages
     * @return The list of messages
     */
    public List<StudentMessage> getStudentMessages(int id) throws DALException
    {
        List<StudentMessage> messages = new ArrayList<>();
        try (Connection con = cm.getConnection())
        {
            String sql = "SELECT * FROM StudentMessage";//WHERE teacherid = ?";
            PreparedStatement ps = con.prepareStatement(sql);
           // ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                StudentMessage msg = new StudentMessage(rs.getInt("teacherId"), rs.getInt("studentId"), rs.getBoolean("status"), rs.getString("message"), rs.getInt("historyId"));
                msg.setHasBeenSeen(rs.getBoolean("seen"));
                msg.setId(rs.getInt("id"));
                messages.add(msg);
            }
        }
        catch (SQLException ex)
        {
            throw new DALException(ex);
        }
        return messages;
    }
    
    
    /**
     * Update an existing StudentMessage object in the database.
     * @param msg The message that will be updated.
     * @throws DALException If something goes wrong during database operations.
     */
    public void updateMessage(StudentMessage msg) throws DALException
    {
        try(Connection con = cm.getConnection())
        {
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
                throw new DALException("Movie could not be edited!");
            }
        }
        catch (SQLException ex)
        {
            throw new DALException(ex);
        }
    }

}
