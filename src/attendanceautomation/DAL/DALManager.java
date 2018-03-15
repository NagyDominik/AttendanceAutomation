package attendanceautomation.DAL;

import attendanceautomation.BE.ClassData;
import attendanceautomation.BE.Person;
import attendanceautomation.BE.Student;
import attendanceautomation.BE.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 * COntains mock data (for now)
 *
 * @author sebok
 */
public class DALManager {

    private ConnectionManager cm;

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
        mockTeacher.add(new Teacher("teacher@easv.dk", "Jeppe Moritz"));
        mockTeacher.add(new Teacher("teacher1@easv.dk", "Lars Jorgensen"));
        
        mockClassData = new ArrayList();
        mockClassData.add(new ClassData("CS2017A", new Teacher("teacher@easv.dk", "Jeppe Moritz")));
        mockClassData.add(new ClassData("CS2017B" , new Teacher("teacher@easv.dk", "Jeppe Moritz")));

   
        mockStudent = new ArrayList();
        mockStudent.add(new Student("student@easv.dk", "Thomas White"));
        mockStudent.add(new Student("student1@easv.dk", "Jesper Boo"));
        mockStudent.add(new Student("student2@easv.dk", "Peter Sebok"));
        mockStudent.add(new Student("student3@easv.dk", "Dominik Nagy"));
        mockStudent.add(new Student("student4@easv.dk", "Daniel McAdams"));
        mockStudent.add(new Student("student5@easv.dk", "Bence Matyasi"));

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
}
