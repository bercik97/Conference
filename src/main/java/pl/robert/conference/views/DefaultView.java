package pl.robert.conference.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import pl.robert.conference.shared.GlobalAuthorizationEntryPoint;
import pl.robert.conference.user.domain.UserFacade;

@SpringView(name = "homepage")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultView extends Composite implements View {

    UserFacade facade;

    VerticalLayout root;

    public DefaultView(UserFacade facade) {
        this.facade = facade;

        setupLayout();
        addHeader();

        if (GlobalAuthorizationEntryPoint.isAuthorized()) {
            Button button = new Button("Wyloguj się");

            button.addClickListener(e -> facade.logout());

            root.addComponent(button);
        }
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void addHeader() {
        root.addComponent(new Label("Witamy w aplikacji do zarządzania konferencjami IT!"));

        if (!GlobalAuthorizationEntryPoint.isAuthorized()) {
            Label label = new Label("Zaloguj się aby uzyskać pełny dostęp do aplikacji");

            Button createNewAccount = new Button("Stwórz nowe konto");
            createNewAccount.setStyleName("link");
            createNewAccount.addClickListener(e -> getUI().getNavigator().navigateTo("create-account"));

            Button signIn = new Button("Zaloguj się");
            signIn.setStyleName("link");
            signIn.addClickListener(e -> getUI().getNavigator().navigateTo("sign-in"));

            root.addComponents(
                    label,
                    createNewAccount,
                    signIn
            );
        }
    }
}
