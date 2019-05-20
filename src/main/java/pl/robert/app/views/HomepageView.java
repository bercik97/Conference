package pl.robert.app.views;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.vaadin.ui.Label;
import com.vaadin.ui.Button;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Composite;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.spring.annotation.SpringView;

import pl.robert.app.user.domain.UserFacade;
import pl.robert.app.shared.GlobalAuthorizationEntryPoint;

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
            Button createNewAccountHref = new Button("Stwórz nowe konto");
            createNewAccountHref.setStyleName("link");
            createNewAccountHref.addClickListener(e -> getUI().getNavigator().navigateTo("create-account"));

            Button loginHref = new Button("Zaloguj się");
            loginHref.setStyleName("link");
            loginHref.addClickListener(e -> getUI().getNavigator().navigateTo("login"));

            root.addComponents(
                    new Label("Zaloguj się aby uzyskać pełny dostęp do aplikacji"),
                    getConferenceSchemaHref(),
                    createNewAccountHref,
                    loginHref
            );
        }
    }

    private void authorized() {
        if (GlobalAuthorizationEntryPoint.isAuthorized()) {
            root.addComponent(getConferenceSchemaHref());

            Button subscribeLecturesHref = new Button("Zapisz się na wybrane prelekcje");
            subscribeLecturesHref.setStyleName("link");
            subscribeLecturesHref.addClickListener(e -> getUI().getNavigator().navigateTo("manage-lectures"));

            Button userProfileHref = new Button("Zobacz informacje o swoim profilu");
            userProfileHref.setStyleName("link");
            userProfileHref.addClickListener(e -> getUI().getNavigator().navigateTo("profile"));

            Button logout = new Button("Wyloguj się");
            logout.addClickListener((clickEvent) -> {
                facade.logout();
                Page.getCurrent().reload();
            });

            root.addComponents(
                    userProfileHref,
                    subscribeLecturesHref,
                    logout
            );
        }
    }

    private Button getConferenceSchemaHref() {
        Button button = new Button("Zobacz plan konferencji IT");

        button.setStyleName("link");
        button.addClickListener(e -> getUI().getNavigator().navigateTo("conference-schema"));

        return button;
    }
}
