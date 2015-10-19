package com.uni.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.uni.bo.StudentBO;
import com.uni.model.Course;
import com.uni.model.Student;

@ManagedBean(name = "insertionBean")
@RequestScoped
public class InsertionBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private int mark;
	private int gender;
	
	private List<Course> courseList;
	private String[] selectedItems;
	List<Course> choosenCourses;
	
	private List<Student> studentList;

	private String errorMsg;

	StudentBO sbo = null;
	
	private boolean isSuccessMessage;
	
	public InsertionBean(){
		
	}
	
	@PostConstruct
	public void init(){
		System.out.println("enterd insertion");
	}
	
	public String addStudent(){
		sbo = new StudentBO();
		Student student = new Student();
		student.setFirst_name(firstName);
		student.setLast_name(lastName);
		String isExist = sbo.checkStudent(student);
		String message = "";
		if(isExist == "success"){
			isSuccessMessage = true;
			message = "Student Add fail,Student is alredy exists.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			return "insert";
		}else{
			isSuccessMessage = false;
			
			student.setAge(age);
			student.setMark(mark);
			student.setGender(gender);
			
			choosenCourses = new ArrayList<>();
			for (int i = 0; i < selectedItems.length; i++) {
				int id = 0;
				id = Integer.parseInt(selectedItems[i]);
				Course course = sbo.getCourseById(id);
				choosenCourses.add(course);
			}
			student.setCourseList(choosenCourses);
			
			String result = sbo.addStudent(student);
			if(result == null){
				return null;
			}else if(result.equals("success")){
				message = "Student Add Success";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
				errorMsg = "";
				studentList = sbo.getStudentList();
				return "view";
			}else{
				errorMsg = "student add fail";
				return "insert";
			}
		}
		
	}
	
	
	
	public boolean isSuccessMessage() {
		return isSuccessMessage;
	}

	public void setSuccessMessage(boolean isSuccessMessage) {
		this.isSuccessMessage = isSuccessMessage;
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
	
	
	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
