package com.uni.dao;

import java.sql.Connection;
import java.util.List;

public interface StudentCourseDAO {

	void addStudentCourse(int studentId,List<Integer> StudentCoursesIds,Connection conn);
}
