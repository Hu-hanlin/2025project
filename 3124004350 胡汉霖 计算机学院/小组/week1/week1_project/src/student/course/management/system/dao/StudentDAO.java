package student.course.management.system.dao;

import student.course.management.system.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends BaseDAO {
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setPhone(rs.getString("phone"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, stmt, rs);
        }
        return students;
    }

    public boolean updateStudentPhone(int studentId, String newPhone) {
        String sql = "UPDATE students SET phone = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPhone);
            pstmt.setInt(2, studentId);
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