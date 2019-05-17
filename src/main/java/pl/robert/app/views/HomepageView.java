package pl.robert.app.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;

import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import pl.robert.app.shared.GlobalAuthorizationEntryPoint;
import pl.robert.app.user.domain.UserFacade;
import pl.robert.app.user.domain.query.UserQueryDto;

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

            Button createNewAccountBtn = new Button("Stwórz nowe konto");
            createNewAccountBtn.setStyleName("link");
            createNewAccountBtn.addClickListener(e -> getUI().getNavigator().navigateTo("create-account"));

            Button loginBtn = new Button("Zaloguj się");
            loginBtn.setStyleName("link");
            loginBtn.addClickListener(e -> getUI().getNavigator().navigateTo("login"));

            root.addComponents(
                    label,
                    getConferenceSchemaHref(),
                    createNewAccountBtn,
                    loginBtn
            );
        }
    }

    private void authorized() {
        if (GlobalAuthorizationEntryPoint.isAuthorized()) {
            root.addComponent(getConferenceSchemaHref());

            VerticalLayout layout = new VerticalLayout();

            UserQueryDto dto = facade.read();

            Label name = new Label("Twoje imię: " + dto.getName());
            Label email = new Label("Twój adres email: " + dto.getEmail());

            Button changeEmailBtn = new Button("Zmień email");
            changeEmailBtn.setStyleName("link");
            changeEmailBtn.addClickListener(e -> getUI().getNavigator().navigateTo("change-email"));

            layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

            layout.addComponents(
                    name,
                    email,
                    changeEmailBtn
            );

            Button subscribeLecturesBtn = new Button("Zapisz się na wybrane prelekcje");
            subscribeLecturesBtn.setStyleName("link");
            subscribeLecturesBtn.addClickListener(e -> getUI().getNavigator().navigateTo("subscribe-lectures"));

            Button logoutBtn = new Button("Wyloguj się");
            logoutBtn.addClickListener(e -> facade.logout());

            root.addComponents(
                    layout,
                    subscribeLecturesBtn,
                    logoutBtn
            );
        }
    }

    private Button getConferenceSchemaHref() {
        Button showConferenceSchemaBtn = new Button("Zobacz plan konferencji IT");
        showConferenceSchemaBtn.setStyleName("link");
        showConferenceSchemaBtn.addClickListener(e -> getUI().getNavigator().navigateTo("conference-schema"));

        root.addComponent(showConferenceSchemaBtn);
        return showConferenceSchemaBtn;
    }
}
