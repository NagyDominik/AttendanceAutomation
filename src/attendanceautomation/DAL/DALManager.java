package attendanceautomation.DAL;

import attendanceautomation.BE.AttendanceStatus;
import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.StudentMessage;
import attendanceautomation.BE.Teacher;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * COntains mock data (for now)
 *
 * @author sebok
 */
public class DALManager {
    
    private ConnectionManager cm = ConnectionManager.getInstance();
    private StudentDBManager studentDBManager = new StudentDBManager();
    private TeacherDBManager teacherDBManager = new TeacherDBManager();
    private LocalDataManager ldm = new LocalDataManager();

    public DALManager() {
        
    }

    /**
     * Retrieve a class based on their id.
     *
     * @return The class associated with the id.
     */
    public List<ClassData> getClassData() {
        List<ClassData> classData = new ArrayList();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Class");
            ps.executeQuery();
        }
        catch (Exception e) {
        }
        return classData;
    }

    /**
     * Retrieve a student based on an id.
     *
     * @return A student with the corresponding id.
     */
    public List<Student> getStudent() throws DALException {
        return studentDBManager.getStudentFromDB();
    }

    /**
     *
     * Retrieve a class based on their id.
     *
     * @return The class associated with the id.
     */
    public List<Teacher> getTeacher() throws DALException {
        return teacherDBManager.getTeacherFromDB();
    }

    /**
     * Attempt login with the specified email and password.
     *
     * @param email The specified email.
     * @param password The specified password.
     * @return A string representing the user type - "Teacher" for teachers,
     * "Student" for students or "None" if there is no match for the email.
     */
    public Person attemptLogin(String email, String password) throws DALException {
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Teacher "
                    + "WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Teacher temp = new Teacher();
                temp.setEmail(rs.getString("email"));
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));
                InputStream inputStream = rs.getBinaryStream("image");
                if (inputStream != null)
                {
                    File target = new File("src/img/students/" + temp.getName().replaceAll(" ", "")+".png");
                    target.mkdirs();
                    java.nio.file.Files.copy(inputStream, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    temp.setImageFile(target);
                }
                else
                {
                    File dir = new File("src/img");
                    dir.mkdirs();
                    File imageNotFound = new File(dir, "help.png");
                    temp.setImageFile(imageNotFound);
                }
                return temp;
            }
        }
        catch (Exception e) {
            throw new DALException(e);
        }
        
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student "
                    + "WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student temp = new Student();
                temp.setEmail(rs.getString("email"));
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));
                InputStream inputStream = rs.getBinaryStream("image");
                if (inputStream != null)
                {
                    File target = new File("src/img/students/" + temp.getName().replaceAll(" ", "")+".png");
                    target.mkdirs();
                    java.nio.file.Files.copy(inputStream, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    temp.setImageFile(target);
                }
                else
                {
                    File dir = new File("src/img");
                    dir.mkdirs();
                    File imageNotFound = new File(dir, "help.png");
                    temp.setImageFile(imageNotFound);
                }
                return temp;
            }
        }
        catch (Exception e) {
            throw new DALException(e);
        }
        return null;
    }

    /**
     * Save message to DB
     *
     * @param msg
     * @throws DALException
     */
    public void saveMessage(StudentMessage msg) throws DALException {
        studentDBManager.saveMessage(msg);
    }

    /**
     * Load the messages
     *
     * @param id
     * @return The list of messages
     * @throws DALException
     */
    public List<StudentMessage> getStudentMessages(int id) throws DALException {
        return teacherDBManager.getStudentMessages(id);
    }

    /**
     * Update an existing StudentMessage object in the database.
     *
     * @param msg The message that will be updated.
     * @throws DALException If something goes wrong during database operations.
     */
    public void updateMessage(StudentMessage msg) throws DALException {
        teacherDBManager.updateMessage(msg);
    }

    /**
     * Checks if there are unread messages of teacher with the given id
     *
     * @param id The id of the teacher.
     * @return True if there are unread messages, false otherwise.
     * @throws DALException f something goes wrong during database operations.
     */
    public boolean hasUnreadMessages(int id) throws DALException {
        return teacherDBManager.hasUnreadMessages(id);
    }

    /**
     * Update an already existing attendance status in the database.
     *
     * @param attendatnceStatus The attendance status that will be updated.
     * @throws DALException If something goes wrong during database operations.
     */
    public void updateAttendanceStatus(AttendanceStatus attendatnceStatus) throws DALException {
        teacherDBManager.updateAttendanceStatus(attendatnceStatus);
    }
    
    public void saveImage(Person p) throws DALException {
        if (p instanceof Teacher) {
            Teacher t = (Teacher) p;
            teacherDBManager.saveImage(t);
        } else if (p instanceof Student) {
            Student s = (Student) p;
            studentDBManager.saveImage(s);
        } else {
            throw new DALException("Not supported class! Parameter must be an instance of a Teacher or a Student!");
        }
    }
    
    /**
     * Saves the users email and password to the local drive.
     * 
     * @param email The user's email address
     * @param password The password typed in by the user;
     * @throws DALException If something goes wrong during file operations.
     */
    public void saveLocalData(String email, String password) throws DALException {
        ldm.saveData(email, password);
    }

    
    /**
     * Save history to DB
     * @param status
     * @return a list with history
     * @throws DALException if cannot save into DB
     */
    public List<AttendanceStatus> saveStatus(AttendanceStatus status) throws DALException{
         List<AttendanceStatus> attendance = new ArrayList<>();
         
         try (Connection con = cm.getConnection())
         {
             PreparedStatement ps = con.prepareStatement("INSERT INTO History(id, date, classData, status, studentID, teacherID) VALUES(?, ?, ?, ?, ?, ?)");
              ps.setInt(0, status.getId());
              ps.setString(1, status.getDate());
              ps.setObject(2, status.getClassData());
              ps.setInt(3, status.getStatusAsNumber());
              ps.setInt(4, status.getStudentID());
              ps.setInt(5, status.getTeacherID());
              attendance.add(status);
              ps.executeQuery();
             
         } catch (Exception ex) {
             throw new DALException(ex);
        }
         
        
        return attendance;
    }
    /**
     * Check if a given password is associated with a given email.
     * @param email The email of a person.
     * @param old The (old) hashed password of a person.
     * @return True if the password is associated with the email address, false otherwise.
     */
    public boolean authenticatePassword(String email, String old, boolean isTeacher) throws DALException
    {
        try (Connection con = cm.getConnection())
        {
            if (isTeacher)
            {
                PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM Teacher AS count "
                    + "WHERE email = ? AND password = ? GROUP BY id");
                ps.setString(1, email);
                ps.setString(2, old);
                ResultSet rs = ps.executeQuery();
                rs.next();
                if (rs.getInt("count") == 1)
                {
                    return true;
                }
            }
            else
            {
                PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM Student AS count "
                    + "WHERE email = ? AND password = ? GROUP BY id");
                ps.setString(1, email);
                ps.setString(2, old);
                ResultSet rs = ps.executeQuery();
                rs.next();
                if (rs.getInt("count") == 1)
                {
                    return true;
                }
            }
            
            return false;
        }
        catch(SQLException ex)
        {
            throw new DALException(ex);
        }
    }

    /**
     * Change the password of the given user.
     * @param userId The id of the Person whose password will be changed.
     * @param newPass The new password.
     */
    public void changePassword(int userId, String hash, boolean isTeacher) throws DALException
    {
        if (isTeacher)
        {
            teacherDBManager.changePassword(userId, hash);
        }
        else
        {
            studentDBManager.changePassword(userId, hash);
        }
    }
}
