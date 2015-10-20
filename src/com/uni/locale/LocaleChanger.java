package com.uni.locale;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "localeChanger")
@SessionScoped
public class LocaleChanger implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Locale locale;
	
	@PostConstruct
	public void init() {
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}
	
	public String englishAction(){		
		
		locale = Locale.ENGLISH;
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(locale);
		return null;
	}
	public String arabicAction(){
		
		locale = Locale.forLanguageTag("ar");
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(locale);
		
		return null;
	}

	public Locale getLocale() {
		return locale;
	}	
		
}
