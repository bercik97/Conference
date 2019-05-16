package pl.robert.conference.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.robert.conference.shared.GlobalAuthorizationEntryPoint;

@SpringView(name = "homepage")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultView extends Composite implements View {

    VerticalLayout root;

    public DefaultView() {
        setupLayout();
        addHeader();
        addHrefs();

        if (GlobalAuthorizationEntryPoint.isAuthorized()) {
            Button button = new Button("Wyloguj się");

            button.addClickListener(e -> GlobalAuthorizationEntryPoint.logout());

            root.addComponent(button);
        } else {
            System.out.println("NIEZALOGOWANY");
        }
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
        Button signIn = new Button("Zaloguj się");

        createNewAccount.setStyleName("link");
        createNewAccount.addClickListener(e -> getUI().getNavigator().navigateTo("create-account"));
        signIn.setStyleName("link");
        signIn.addClickListener(e -> getUI().getNavigator().navigateTo("sign-in"));

        root.addComponents(
                createNewAccount,
                signIn
        );
    }
}
