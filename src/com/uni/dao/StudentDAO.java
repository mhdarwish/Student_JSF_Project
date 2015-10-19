package com.uni.dao;

import java.util.List;

import com.uni.model.Course;
import com.uni.model.Student;

public interface StudentDAO {

	List<Student> getAllStudent();
	int addStudent(Student student);
	int updateStudent(Student student);
	int deleteStudent(Student student);
	Student getStudentById(int studentId);
	List<Course> getStudentCourse(int studentId);
}
