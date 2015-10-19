package com.uni.dao.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uni.dao.CourseDAO;
import com.uni.model.Course;
import com.uni.util.DBUtil;

public class CourseDAOImpl implements CourseDAO,Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Course> getAllCourses() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from test_course";
		List<Course> courseList = new ArrayList<Course>();
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getInt(1));
				course.setName(rs.getString(2));
				courseList.add(course);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
		return courseList;
	}

	@Override
	public Course getCourseById(int courseId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from test_course where id = ?";
		Course course = new Course();
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, courseId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				course.setId(rs.getInt(1));
				course.setName(rs.getString(2));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
		return course;
	}
}
