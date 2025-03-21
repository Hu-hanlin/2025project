package student.course.management.system.service;

import student.course.management.system.dao.CourseDAO;
import student.course.management.system.dao.StudentCourseDAO;
import student.course.management.system.dao.StudentDAO;
import student.course.management.system.model.Course;
import student.course.management.system.model.Student;
import student.course.management.system.model.StudentCourse;

import java.util.List;

public class AdminService {
    private StudentDAO studentDAO = new StudentDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private StudentCourseDAO studentCourseDAO = new StudentCourseDAO();

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public boolean updateStudentPhone(int studentId, String newPhone) {
        return studentDAO.updateStudentPhone(studentId, newPhone);
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }

    public boolean deleteCourse(int courseId) {
        return courseDAO.deleteCourse(courseId);
    }

    public List<StudentCourse> getStudentsByCourse(int courseId) {
        return studentCourseDAO.getStudentsByCourse(courseId);
    }

    public List<StudentCourse> getCoursesByStudent(int studentId) {
        return studentCourseDAO.getCoursesByStudent(studentId);
    }
}    