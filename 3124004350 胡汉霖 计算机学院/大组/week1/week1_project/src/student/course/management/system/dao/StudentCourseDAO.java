package student.course.management.system.dao;

import student.course.management.system.model.StudentCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDAO extends BaseDAO {
    public List<StudentCourse> getStudentsByCourse(int courseId) {
        String sql = "SELECT * FROM student_courses WHERE course_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, courseId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentCourse sc = new StudentCourse();
                sc.setStudentId(rs.getInt("student_id"));
                sc.setCourseId(rs.getInt("course_id"));
                studentCourses.add(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return studentCourses;
    }

    public List<StudentCourse> getCoursesByStudent(int studentId) {
        String sql = "SELECT * FROM student_courses WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentCourse sc = new StudentCourse();
                sc.setStudentId(rs.getInt("student_id"));
                sc.setCourseId(rs.getInt("course_id"));
                studentCourses.add(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return studentCourses;
    }

    public boolean selectCourse(int studentId, int courseId) {
        String sql = "INSERT INTO student_courses (student_id, course_id) VALUES (?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }
        return false;
    }

    public boolean dropCourse(int studentId, int courseId) {
        String sql = "DELETE FROM student_courses WHERE student_id = ? AND course_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }
        return false;
    }
}    