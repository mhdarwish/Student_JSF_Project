package com.uni.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.uni.validator.PasswordValidator")
public class PasswordValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		String password = (String)value;
		if(password.contains("@")){
			throw new ValidatorException(new FacesMessage("Passwords cannot contain @")); 
		}
	}

}
