package com.uni.dao;

import java.util.List;

import com.uni.model.Course;

public interface CourseDAO {

	List<Course> getAllCourses();
	Course getCourseById(int courseId);
}
