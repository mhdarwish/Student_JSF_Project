package com.uni.dao.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uni.dao.StudentDAO;
import com.uni.model.Course;
import com.uni.model.Student;
import com.uni.util.DBUtil;

public class StudentDAOImpl implements StudentDAO,Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Student> getAllStudent() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from test_student";
		List<Student> studentList = new ArrayList<Student>();
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt(1));
				student.setFirst_name(rs.getString(2));
				student.setLast_name(rs.getString(3));
				student.setAge(rs.getInt(4));
				student.setMark(rs.getInt(5));
				student.setGender(rs.getInt(6));
				studentList.add(student);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
		return studentList;
	}

	@SuppressWarnings("resource")
	@Override
	public int addStudent(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = -1;
		try {
			String sqlStudent = "INSERT INTO TEST_STUDENT (ID,FIRST_NAME,LAST_NAME,AGE,MARK,GENDER) VALUES (?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlStudent);
			int stdId = getStudentSequence();
			stmt.setInt(1, stdId);
			stmt.setString(2, student.getFirst_name());
			stmt.setString(3, student.getLast_name());
			stmt.setInt(4, student.getAge());
			stmt.setInt(5, student.getMark());
			stmt.setInt(6, student.getGender());
			result = stmt.executeUpdate();

			String sqlStudentCourse = "INSERT INTO TEST_STD_CRS (ID,CRS_ID,STD_ID) VALUES (?,?,?)";
			stmt = conn.prepareStatement(sqlStudentCourse);
			List<Course> courseList = student.getCourseList();
			for (int i = 0; i < courseList.size(); i++) {
				stmt.setInt(1, getStudentCourseSequence());
				stmt.setInt(2, courseList.get(i).getId());
				stmt.setInt(3, stdId);
				result = stmt.executeUpdate();
				System.out.println("course insertion result: " + result);
			}
			conn.commit();
		} catch (SQLException ex) {
			DBUtil.doRollBack(conn);
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
		return result;
	}

	@SuppressWarnings("resource")
	@Override
	public int updateStudent(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = -1;
		try {
			String sqlStudent = "UPDATE TEST_STUDENT SET FIRST_NAME = ?, LAST_NAME = ?, AGE = ?, MARK = ?, GENDER = ? WHERE ID = ?";
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlStudent);
			stmt.setString(1, student.getFirst_name());
			stmt.setString(2, student.getLast_name());
			stmt.setInt(3, student.getAge());
			stmt.setInt(4, student.getMark());
			stmt.setInt(5, student.getGender());
			stmt.setInt(6, student.getId());
			result = stmt.executeUpdate();

			String sqldeletePreviousCourses = "DELETE FROM TEST_STD_CRS WHERE STD_ID = ?";
			stmt = conn.prepareStatement(sqldeletePreviousCourses);
			List<Course> courseList = student.getCourseList();
			for(int i=0;i<courseList.size();i++){
				stmt.setInt(1, student.getId());
				stmt.executeUpdate();
			}
			
			String sqlInsert = "INSERT INTO TEST_STD_CRS (ID,CRS_ID,STD_ID) VALUES (?,?,?)";
			stmt = conn.prepareStatement(sqlInsert);
			for(int i=0;i<courseList.size();i++){
				stmt.setInt(1, getStudentCourseSequence());
				stmt.setInt(2, courseList.get(i).getId());
				stmt.setInt(3, student.getId());
				result = stmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException ex) {
			DBUtil.doRollBack(conn);
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
		return result;
	}

	@SuppressWarnings("resource")
	@Override
	public int deleteStudent(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
//		int result = -1/0;
		int result = -1;
		try {
			String sqlStudent = "DELETE FROM TEST_STUDENT WHERE ID = ?";
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlStudent);
			stmt.setInt(1, student.getId());
			result = stmt.executeUpdate();

			String sqlStudentCourse = "DELETE FROM TEST_STD_CRS WHERE STD_ID = ?";
			stmt = conn.prepareStatement(sqlStudentCourse);
			stmt.setInt(1, student.getId());
			result = stmt.executeUpdate();
			conn.commit();
		} catch (SQLException ex) {
			DBUtil.doRollBack(conn);
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
		return result;
	}

	@Override
	public Student getStudentById(int studentId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from test_student where id = ?";
		Student student = new Student();
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				student.setId(rs.getInt(1));
				student.setFirst_name(rs.getString(2));
				student.setLast_name(rs.getString(3));
				student.setAge(rs.getInt(4));
				student.setMark(rs.getInt(5));
				student.setGender(rs.getInt(6));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
		return student;
	}

	public int getStudentSequence() {
		int studentId = -1;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT TEST_STD.nextVal FROM DUAL";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				studentId = rs.getInt(1);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
		return studentId;
	}

	public int getStudentCourseSequence() {
		int studentCourseId = -1;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT CRS_STD.nextVal FROM DUAL";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				studentCourseId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
		return studentCourseId;
	}

	public List<Integer> getStudentCoursIds(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Integer> ids = new ArrayList<Integer>();
		String sql = "SELECT ID FROM TEST_STD_CRS WHERE CRS_ID = ?, STD_ID = ?";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			List<Course> courseList = student.getCourseList();
			for (int i = 0; i < courseList.size(); i++) {
				stmt.setInt(1, courseList.get(i).getId());
				stmt.setInt(2, student.getId());
				rs = stmt.executeQuery();
				if (rs.next()) {
					int id = -1;
					id = rs.getInt(1);
					ids.add(id);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeOperations(conn, stmt);
		}
		return ids;
	}

	public List<Course> getStudentCourse(int studentId){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "Select CRS_ID from TEST_STD_CRS where STD_ID = ?";
		List<Course> studentCourseList = new ArrayList<>();
		Course c = null;
		try{
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			rs = stmt.executeQuery();
			while(rs.next()){
				c = new Course();
				c.setId(rs.getInt(1));
				studentCourseList.add(c);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return studentCourseList;
		
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		String sql = "SELECT CRS_ID from TEST_STD_CRS where STD_ID = ?";
//		List<Course> courseIds = new ArrayList<>();
//		List<Course> courseNames = new ArrayList<>();
//		try{
//			conn = DBUtil.getConnection();
//			stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, studentId);
//			rs = stmt.executeQuery();
//			while(rs.next()){
//				Course crs = new Course();
//				crs.setId(rs.getInt(1));
//				courseIds.add(crs);
//			}
//			
//			sql = "select NAME from TEST_COURSE where ID = ?";
//			stmt = conn.prepareStatement(sql);
//			for(int i=0;i<courseIds.size();i++){
//				stmt.setInt(1, courseIds.get(i).getId());
//				rs = stmt.executeQuery();
//				while(rs.next()){
//					Course c = new Course();
//					c.setName(rs.getString(1));
//					courseNames.add(c);
//				}
//			}
//		}catch(SQLException ex){
//			ex.printStackTrace();
//		}
//		return courseNames;
	}
	
	public String login(Student student){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select first_name, id from TEST_STUDENT where first_name = ? and id = ?";
		String result = "";
		try{
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, student.getFirst_name());
			stmt.setInt(2, student.getId());
			rs = stmt.executeQuery();			
			if(rs.next()){
				result = "success";
			}else{
				result = "fail";
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			DBUtil.closeOperations(conn, stmt);
		}
		return result;
	}
	
	public int checkStudent(Student student){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM TEST_STUDENT WHERE FIRST_NAME = ? AND LAST_NAME = ?";
		int result = 0;
		try{
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, student.getFirst_name());
			stmt.setString(2, student.getLast_name());
			rs = stmt.executeQuery();
			if(rs.next()){
				result = 1;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			DBUtil.closeOperations(conn, stmt);
		}
		return result;
	}
	
//	public static void main(String[] args) {
//		Student std = new Student();
//		std.setFirst_name("tala");
//		std.setLast_name("qasem");
//		StudentDAOImpl sdi = new StudentDAOImpl();
//		int result = sdi.checkStudent(std);
//		System.out.println(result);
//	}
}
