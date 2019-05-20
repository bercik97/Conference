package pl.robert.app.views;

import com.vaadin.ui.UI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;

@SpringUI
@SpringViewDisplay
class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        getUI().getNavigator().navigateTo("homepage");
        getNavigator().setErrorView(VaadinUI.getCurrent().getNavigator().getCurrentView());
    }
}
