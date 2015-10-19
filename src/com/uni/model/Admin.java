package com.uni.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.uni.util.AdminRegistration;

@ManagedBean
@SessionScoped
public class Admin {

	private String name;
	private String password;
	
	public Admin(){
		
	}
	public Admin(String name,String password){
		this.name = name;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String register(){
		AdminRegistration.register(name, password);
		return "loginComp";
	}
	public String login(){
		
		return "view";
	}
	public String logout(){
		name = "";
		password = "";
		return "loginComp";
	}
	
}
