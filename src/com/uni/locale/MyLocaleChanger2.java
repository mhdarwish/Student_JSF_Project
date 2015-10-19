package com.uni.locale;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "localeChanger2")
@SessionScoped
public class MyLocaleChanger2 implements Serializable{

	private static final long serialVersionUID = 1L;

	private Locale locale;
	
	@PostConstruct
	public void init(){
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}
	
	public String englishAction(){
		locale = Locale.ENGLISH;
		
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		
		
		return null;
	}
	public String arabicAction(){
		
		locale = Locale.forLanguageTag("ar");
		
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		
		return null;
	}
	
	public Locale getLocale(){
		return locale;
	}
	
}
