package com.uni.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.uni.dao.StudentCourseDAO;
import com.uni.util.DBUtil;

public class StudentCourseDAOImpl implements StudentCourseDAO{

	@Override
	public void addStudentCourse(int studentId,
			List<Integer> StudentCoursesids, Connection conn) {
		PreparedStatement stmt = null;
		String sql = "insert into test_std_crs (id,crs_id,std_id) values (?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			for (int i = 0; i < StudentCoursesids.size(); i++) {
				stmt.setInt(2, StudentCoursesids.get(i));
			}
			stmt.setInt(3, studentId);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
	}
}
