package listener;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import com.uni.util.AdminRegistration;

public class LoginActionListener implements ActionListener {

	@Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		System.out.println("inside login listener");
		UIComponent container = event.getComponent().getNamingContainer();
		String name = (String) ((UIInput) container.findComponent("form:name"))
				.getValue();
		String password = (String) ((UIInput) container
				.findComponent("form:password")).getValue();
		if (AdminRegistration.isRegistered(name, password)) {
			return;
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(container.getClientId(), new FacesMessage(
				"Name/password are invalid. Please try again."));
		throw new AbortProcessingException("Invalid credentials");
	}

}
