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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static DALManager instance;
    private List<Student> student = new ArrayList();
    private List<Teacher> teacher = new ArrayList();
    private List<ClassData> classData = new ArrayList();

    public static DALManager getInstance() throws DALException {
        if (instance == null) {
            instance = new DALManager();
        }
        return instance;
    }

    private DALManager() throws DALException {
        try {
            loadAllData();
        }
        catch (DALException ex) {
            throw new DALException(ex);
        }
    }

    /**
     * Returns a list of class data.
     *
     * @return A list of class data.
     * @throws DALException If something goes wrong during database operations.
     */
    public List<ClassData> getClassData() throws DALException {
        if (classData.isEmpty()) {
            try (Connection con = cm.getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Class");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ClassData temp = new ClassData();
                    temp.setId(rs.getInt("id"));
                    temp.setClassName(rs.getString("name"));
                    classData.add(temp);
                }
            }
            catch (Exception e) {
                throw new DALException(e);
            }
        }
        return classData;
    }

    private void loadAllData() throws DALException {
        getClassData();
        teacher.addAll(teacherDBManager.getTeacherFromDB());
        student.addAll(studentDBManager.getStudentFromDB());
        getStatus();

        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ClassTeacher");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                for (Teacher tempTeacher : teacher) {
                    if (rs.getInt("teacherid") == tempTeacher.getId()) {
                        for (ClassData tempClass : classData) {
                            if (rs.getInt("classid") == tempClass.getId()) {
                                tempClass.setTeacher(tempTeacher);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            throw new DALException(ex);
        }

        student.forEach((tempStudent) -> {
            classData.forEach((tempClass) -> {
                if (tempStudent.getClassID() == tempClass.getId()) {
                    tempClass.addStudent(tempStudent);
                }
            });
        });
    }

    /**
     * Retrieve a student based on an id.
     *
     * @return A student with the corresponding id.
     */
    public List<Student> getStudent() throws DALException {
        return studentDBManager.getStudentFromDB();
        //return student;
    }

    /**
     *
     * Retrieve a class based on their id.
     *
     * @return The class associated with the id.
     */
    public List<Teacher> getTeacher() throws DALException {
        return teacherDBManager.getTeacherFromDB();
        //return teacher;
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
            String sql = "SELECT id FROM Teacher WHERE email = ? and password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                for (Teacher teacher1 : teacher) {
                    if (teacher1.getId() == rs.getInt("id")) {
                        return teacher1;
                    }
                }
            }

            sql = "SELECT id FROM Student WHERE email = ? and password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                for (Student student1 : student) {
                    if (student1.getId() == rs.getInt("id")) {
                        return student1;
                    }
                }
            }
        }
        catch (SQLException ex) {
            throw new DALException(ex);
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

    public String[] getLocalData() throws DALException {
        return ldm.getLocalData();
    }

    /**
     * Save history to DB
     *
     * @param status of the students
     * @throws DALException if cannot save into DB
     */
    public void saveStatus(AttendanceStatus status, Student student) throws DALException {
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO History(id, date, status, studentid, teacherset) VALUES(?, ?, ?, ?, ?)");
            ps.setInt(0, status.getId());
            ps.setString(1, status.getDate());
            ps.setInt(2, status.getStatusAsNumber());
            ps.setInt(3, student.getId());
            ps.setBoolean(4, status.isTeacherSet());
            ps.executeUpdate();
        }
        catch (Exception ex) {
            throw new DALException(ex);
        }
    }

    private void getStatus() throws DALException {
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM History");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AttendanceStatus sts = new AttendanceStatus(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getInt("status"), rs.getBoolean("teacherset"));
                student.forEach((student) -> {
                    try {
                        if (rs.getInt("studentid") == student.getId()) {
                            student.addHistory(sts);
                        }
                    }
                    catch (SQLException ex) {
                        Logger.getLogger(DALManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }
        catch (SQLException ex) {
            throw new DALException(ex);
        }
    }

    /**
     * Check if a given password is associated with a given email.
     *
     * @param email The email of a person.
     * @param old The (old) hashed password of a person.
     * @return True if the password is associated with the email address, false
     * otherwise.
     */
    public boolean authenticatePassword(String email, String old, boolean isTeacher) throws DALException {
        try (Connection con = cm.getConnection()) {
            if (isTeacher) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Teacher "
                        + "WHERE email = ? AND password = ?");
                ps.setString(1, email);
                ps.setString(2, old);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
                return false;
            } else {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Student "
                        + "WHERE email = ? AND password = ?");
                ps.setString(1, email);
                ps.setString(2, old);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
                return false;
            }
        }
        catch (SQLException ex) {
            throw new DALException(ex);
        }
    }

    /**
     * Change the password of the given user.
     *
     * @param userId The id of the Person whose password will be changed.
     * @param newPass The new password.
     */
    public void changePassword(int userId, String hash, boolean isTeacher) throws DALException {
        if (isTeacher) {
            teacherDBManager.changePassword(userId, hash);
        } else {
            studentDBManager.changePassword(userId, hash);
        }
    }

    public void clearLocalData() throws DALException {
        ldm.clearData();
    }
}
