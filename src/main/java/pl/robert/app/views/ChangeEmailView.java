package pl.robert.app.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button;

import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import pl.robert.app.shared.GlobalAuthorizationEntryPoint;
import pl.robert.app.shared.NotificationService;
import pl.robert.app.user.domain.UserFacade;

@SpringView(name = "change-email")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ChangeEmailView extends Composite implements View {

    UserFacade facade;

    VerticalLayout root;

    public ChangeEmailView(UserFacade facade) {
        this.facade = facade;

        setupLayout();
        unauthorized();
        authorized();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void unauthorized() {
        if (!GlobalAuthorizationEntryPoint.isAuthorized()) {
            NotificationService.showErrorNotification("Tylko zalogowani użytkownicy mogą zmienić adres email");
            root.addComponents(new Label("Błąd 403: Odmowa dostępu"));
            addHomepageHref();
        }
    }

    private void addHomepageHref() {
        Button button = new Button("Idź do strony głównej");

        button.setStyleName("link");
        button.addClickListener(e -> getUI().getNavigator().navigateTo("homepage"));

        root.addComponent(button);
    }

    private void authorized() {
        if (GlobalAuthorizationEntryPoint.isAuthorized()) {
            addHeader();
            addForm();
            addUserProfileHref();
        }
    }

    private void addHeader() {
        root.addComponent(new Label("Zmień email"));
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();

        TextField newEmail = new TextField("Nowy email");
        Button change = new Button("Zmień");

        change.addClickListener((event) -> {
            facade.update(GlobalAuthorizationEntryPoint.name, newEmail.getValue());
            NotificationService.showHumanizedNotification("Zmieniłeś swój email!");
        });

        formLayout.addComponents(
                newEmail,
                change
        );

        root.addComponent(formLayout);
    }

    private void addUserProfileHref() {
        Button button = new Button("Wróć do profilu użytkownika");

        button.setStyleName("link");
        button.addClickListener(e -> getUI().getNavigator().navigateTo("profile"));

        root.addComponent(button);
    }
}
