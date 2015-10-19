package com.uni.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.uni.dao.jdbc.CourseDAOImpl;
import com.uni.dao.jdbc.StudentDAOImpl;
import com.uni.model.Course;
import com.uni.model.Student;

public class StudentBO implements Serializable {

	private static final long serialVersionUID = 1L;

	// for the insertion page.
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private int mark;
	private int gender;
	private List<Course> courseList;
	private String[] selectedItems;
	List<Course> choosenCourses;

	// for the view page.
	private List<Student> studentList = new ArrayList<>();

	StudentDAOImpl stdDAO = new StudentDAOImpl();
	CourseDAOImpl crsDAO = new CourseDAOImpl();

	public StudentBO() {

	}

	public String addStudent(Student student) {

		boolean validationResult = validateStudent(student);
		if (validationResult) {
			int result = stdDAO.addStudent(student);

			// TODO: Move To view tier
			if (result > 0 && result < 10) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return null;
		}
	}

	private boolean validateStudent(Student student) {
		boolean result = true;
		
		if (student == null) {
			result = false;
			throw new IllegalArgumentException("Student is null");
		}
		if (student.getFirst_name().equals("")
				|| student.getFirst_name() == null) {
			result = false;
			throw new IllegalArgumentException("first name is null");
		}
		if (student.getLast_name().equals("")
				|| student.getLast_name() == null) {
			result = false;
			throw new IllegalArgumentException("first name is null");
		}
		if (student.getAge() <= 0) {
			result = false;
			throw new IllegalArgumentException("Age is not valid");
		}
		if (student.getMark() <= 0) {
			result = false;
			throw new IllegalArgumentException("Mark is not valid");
		}
		if (student.getGender() != 0 && student.getGender() != 1) {
			result = false;
			throw new IllegalArgumentException("Gender is not valid");
		}
	
		return result;

	}

	public String updateStudent(Student student) {
		boolean validationResult = validateStudent(student);
		if (validationResult) {
			int result = stdDAO.updateStudent(student);
			if (result > 0 && result < 10) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return null;
		}
	}

	public String delete(int id) {
		Student std = stdDAO.getStudentById(id);
		int result = stdDAO.deleteStudent(std);
		if (result > 0 && result < 10) {
			return "success";
		} else {
			return "fail";
		}
	}

	public String checkStudent(Student student) {

		int result = stdDAO.checkStudent(student);
		if (result == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

	public void handleException(Throwable exception) {
		String message = "";
		if (exception instanceof Exception) {
			message = "An Application Error accured : "
					+ exception.getMessage();
		} else {
			message = "An unexpected error accoured !";
		}
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public String login(Student std) {
		String result = stdDAO.login(std);
		return result;
	}

	public Student getStudentById(int id) {
		Student student = stdDAO.getStudentById(id);
		return student;
	}

	public Course getCourseById(int id) {
		Course course = crsDAO.getCourseById(id);
		return course;
	}

	public List<Student> getStudentList() {
		studentList = stdDAO.getAllStudent();
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public List<Course> getCourseList() {
		courseList = crsDAO.getAllCourses();
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	public List<Course> getChoosenCourses() {
		return choosenCourses;
	}

	public void setChoosenCourses(List<Course> choosenCourses) {
		this.choosenCourses = choosenCourses;
	}

}
