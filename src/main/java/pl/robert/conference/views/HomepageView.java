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
import pl.robert.conference.user.domain.dto.UserDto;

@SpringView(name = "homepage")
@FieldDefaults(level = AccessLevel.PRIVATE)
class HomepageView extends Composite implements View {

    UserFacade facade;

    VerticalLayout root;

    public HomepageView(UserFacade facade) {
        this.facade = facade;

        setupLayout();
        addHeader();
        unauthorized();
        authorized();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void addHeader() {
        root.addComponent(new Label("Witamy w aplikacji do zarządzania konferencjami IT!"));
    }

    private void unauthorized() {
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

    private void authorized() {
        if (GlobalAuthorizationEntryPoint.isAuthorized()) {
            VerticalLayout layout = new VerticalLayout();

            UserDto dto = facade.read();

            Label name = new Label("Twoje imię: " + dto.getName());
            Label email = new Label("Twój adres email: " + dto.getEmail());

            layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

            layout.addComponents(
                    name,
                    email
            );

            Button changeEmailBtn = new Button("Zmień email");
            changeEmailBtn.setStyleName("link");
            changeEmailBtn.addClickListener(e -> getUI().getNavigator().navigateTo("change-email"));

            Button logoutBtn = new Button("Wyloguj się");
            logoutBtn.addClickListener(e -> facade.logout());

            root.addComponents(
                    layout,
                    changeEmailBtn,
                    logoutBtn
            );
        }
    }
}
