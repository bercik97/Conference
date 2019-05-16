package pl.robert.conference.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@SpringView(name = "homepage")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultView extends Composite implements View {

    VerticalLayout root;

    public DefaultView() {
        setupLayout();
        addHeader();
        addHrefs();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void addHeader() {
        root.addComponent(new Label("Witamy w aplikacji do zarządzania konferencjami IT!"));
    }

    private void addHrefs() {
        Button createNewAccount = new Button("Stwórz nowe konto");

        createNewAccount.setStyleName("link");
        createNewAccount.addClickListener(e -> getUI().getNavigator().navigateTo("create-account"));

        root.addComponent(createNewAccount);
    }
}
