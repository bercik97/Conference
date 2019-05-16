package pl.robert.conference.views;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.UI;

@SpringUI
@SpringViewDisplay
public class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        getUI().getNavigator().navigateTo("homepage");
    }
}
