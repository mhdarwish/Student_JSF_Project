package listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.uni.locale.LocaleChanger;
import com.uni.locale.MyLocaleChanger2;

public class MyPhaseListenerTestMOH implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		updateViewRootLocale(event.getFacesContext());
	}

	@Override
	public void beforePhase(PhaseEvent event) {

		updateViewRootLocale(event.getFacesContext());

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateViewRootLocale(FacesContext context) {

		MyLocaleChanger2 localeBean = context.getApplication()
				.evaluateExpressionGet(context, "localeChanger2",
						MyLocaleChanger2.class);
		if (localeBean != null) {
			FacesContext.getCurrentInstance().getViewRoot()
					.setLocale(localeBean.getLocale());
		}
	}

}
