package com.uni.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;

import com.uni.bo.StudentBO;
import com.uni.model.Course;
import com.uni.model.Student;

@ManagedBean(name = "studentBean")
@ViewScoped
public class StudentBean implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private int id;
	private List<Student> studentList;
	private List<Course> courseList;
	private String[] selectedItems;
	List<Course> choosenCourses;
	private String errorMsg;
	
	private Student student;
	
	////Data model:
//	Student[] studentArray = null;
//	private DataModel<Student> model = null;
//	public DataModel<Student> getStudentArray(){
//		if (model == null) {
//	        model = new ArrayDataModel<Student>(studentArray);
//	    }
//		return model;
//	}
	
	private Date time;
	
	
	StudentBO sbo = null;
	
	public StudentBean(){
	
	}
	
	@PostConstruct
	public void init(){
		time = new Date();
		sbo = new StudentBO();
		studentList = sbo.getStudentList();
		courseList = sbo.getCourseList();
		
//		studentArray = studentList.toArray(new Student[studentList.size()]);
//		model = new ArrayDataModel<Student>(studentArray);
	}
	
	
	public String delete() throws IllegalArgumentException{
		System.out.println("inside delete method");
		PartialViewContext pvc = FacesContext.getCurrentInstance().getPartialViewContext();
		if(pvc.isPartialRequest()){
			del();
			
		}else{
			del();
		}
		return "view";
	}
	private void del(){
		sbo = new StudentBO();
		String result = sbo.delete(id);
//		String result = sbo.delete(-12);//here some sql exception should happen
		if(result.equals("success")){
			studentList = sbo.getStudentList();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student deleted Successfully"));
		}else{
			throw new IllegalArgumentException("can't delete this record....");
		}
	}
	
	public String updateStudent(){
		student.setCanEdit(true);
		return null;
	}
	
	public String saveStudent(){
		for(Student student : studentList){
			student.setCanEdit(false);
		}
		
		choosenCourses = new ArrayList<>();
		for (int i = 0; i < selectedItems.length; i++) {
			int id = 0;
			id = Integer.parseInt(selectedItems[i]);
			Course course = sbo.getCourseById(id);
			choosenCourses.add(course);
		}
		student.setId(id);
		student.setCourseList(choosenCourses);
		
		String result = sbo.updateStudent(student);
		if(result.equals("success")){
			return null;
		}else{
			errorMsg = "student update fail";
			return "index";
		}
	}
	
	public String getMessage(){
		if(student.getFirst_name() == null){
			return "";
		}else{
			return student.getFirst_name()+" action success";
		}
		
	}
	public String logout(){
		return "loginComp";
	}
	
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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
