package student.course.management.system.service;

import student.course.management.system.dao.CourseDAO;
import student.course.management.system.dao.StudentCourseDAO;
import student.course.management.system.model.Course;
import student.course.management.system.model.StudentCourse;

import java.util.List;

public class StudentService {
    private CourseDAO courseDAO = new CourseDAO();
    private StudentCourseDAO studentCourseDAO = new StudentCourseDAO();

    public List<Course> getAvailableCourses() {
        return courseDAO.getAllCourses();
    }

    public boolean selectCourse(int studentId, int courseId) {
        return studentCourseDAO.selectCourse(studentId, courseId);
    }

    public boolean dropCourse(int studentId, int courseId) {
        return studentCourseDAO.dropCourse(studentId, courseId);
    }

    public List<StudentCourse> getSelectedCourses(int studentId) {
        return studentCourseDAO.getCoursesByStudent(studentId);
    }
}    