package listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.uni.locale.LocaleChanger;

public class MyPhaseListener implements PhaseListener {
	
	private static final long serialVersionUID = 1L;
	
	private static final String LOCALE_BEAN_NAME = "localeChanger";
	
	@Override
	public void afterPhase(PhaseEvent event) {
		updateViewRootLocale(event.getFacesContext());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		updateViewRootLocale(event.getFacesContext());		
	}

	@Override
	public PhaseId getPhaseId() {

		return PhaseId.PROCESS_VALIDATIONS;
	}
	
	 /**
     * Search for managed Bean by its name
     *
     * @param fc Faces Context
     * @param managedBeanName managed bean name
     * @return object of managed Bean
     */
    private void updateViewRootLocale(FacesContext context) {
    	
    	LocaleChanger localeBean = context.getApplication().evaluateExpressionGet(context, String.format("#{%s}", LOCALE_BEAN_NAME), LocaleChanger.class);
    	if(localeBean != null) {
    		FacesContext.getCurrentInstance().getViewRoot().setLocale(localeBean.getLocale());
    	}
    }
}
