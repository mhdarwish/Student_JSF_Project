package com.uni.model;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String first_name;
	private String last_name;
	private int age;
	private int mark;
	private int gender;
	private List<Course> courseList;
	private boolean canEdit;
	
	public Student(int id,String first_name,String last_name,int age,int mark,int gender,List<Course> courseList){
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.mark = mark;
		this.gender = gender;
		this.courseList = courseList;
		this.canEdit = false;
	}
	public Student(){
		this.canEdit = false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
	public boolean isCanEdit() {
		return canEdit;
	}
	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}
}
