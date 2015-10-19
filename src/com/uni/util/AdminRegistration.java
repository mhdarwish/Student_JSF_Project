package com.uni.util;

import java.util.ArrayList;
import java.util.List;

import com.uni.model.Admin;

public class AdminRegistration {

	private static List<Admin> registeredAdmin = new ArrayList<>();
	
	static{
		registeredAdmin.add(new Admin("mohammed", "pass"));
	}
	
	public static void register(String name,String password){
		registeredAdmin.add(new Admin(name, password));
	}
	
	public static boolean isRegistered(String name,String password){
		for(Admin admin : registeredAdmin){
			if(admin.getName().equals(name) && admin.getPassword().equals(password)){
				return true;
			}
		}
		return false;
	}
}
