package com.uni.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import com.uni.bo.StudentBO;
import com.uni.model.Course;
import com.uni.model.Student;

@ManagedBean(name = "updateBean")
@RequestScoped
public class UpdateBean implements Serializable {

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

	StudentBO sbo = new StudentBO();

	public UpdateBean() {
	
	}

	@PostConstruct
	public void init() {		
		System.out.println(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
	} 
	
	public void preRender(ComponentSystemEvent event) {
		
		FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.forLanguageTag("ar"));
	}
	
	public void setData(){
		Student std = sbo.getStudentById(id);
		id = std.getId();
		firstName = std.getFirst_name();
		lastName = std.getLast_name();
		age = std.getAge();
		mark = std.getMark();
		gender = std.getGender();
		courseList = std.getCourseList();
	}
	
	public String navigateToUpdate() {
		
		setData();
		
		return "update";
	}

	public String updateStudent() throws IllegalArgumentException {
		String strId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		id = Integer.parseInt(strId);
		
		sbo = new StudentBO();
		Student student = new Student();
		student.setId(id);
		student.setFirst_name(firstName);
		student.setLast_name(lastName);
		student.setAge(age);
		student.setMark(mark);
		student.setGender(gender);
		String message = "";

		choosenCourses = new ArrayList<>();
		for (int i = 0; i < selectedItems.length; i++) {
			int id = 0;
			id = Integer.parseInt(selectedItems[i]);
			Course course = sbo.getCourseById(id);
			choosenCourses.add(course);
		}
		student.setCourseList(choosenCourses);

		String result = sbo.updateStudent(student);
		if(result == null){
			return null;
		}else if (result.equals("success")) {
			message = "Student Updated Success";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			
			errorMsg = "Student Update Successfuly";
			studentList = sbo.getStudentList();
			return "view";
		} else {
			errorMsg = "Student Update Fail,Please try again";
			return "update";
		}
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
