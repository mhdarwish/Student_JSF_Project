package com.uni.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.uni.bo.StudentBO;
import com.uni.model.Student;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private int id;
	
	private String message = "";
	
	public String login(){
		StudentBO sbo = new StudentBO();
		Student std = new Student();
		std.setFirst_name(firstName);
		std.setId(id);
		String result = sbo.login(std);
		if(result.equals("fail")){
			message = "please enter a valid username/password";
			return "login";
		}else{
			return "view";
		}
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
