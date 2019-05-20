package pl.robert.app.views;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.vaadin.ui.Label;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Composite;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;

import pl.robert.app.user.domain.UserFacade;
import pl.robert.app.shared.NotificationService;
import pl.robert.app.shared.ParameterizedException;
import pl.robert.app.user.domain.dto.CreateUserDto;
import pl.robert.app.shared.GlobalAuthorizationEntryPoint;

@SpringView(name = "create-account")
@FieldDefaults(level = AccessLevel.PRIVATE)
class CreateAccountView extends Composite implements View {

    UserFacade facade;

    VerticalLayout root;

    public CreateAccountView(UserFacade facade) {
        this.facade = facade;

        setupLayout();
        unauthorized();
        authorized();
        addHomepageHref();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void unauthorized() {
        if (!GlobalAuthorizationEntryPoint.isAuthorized()) {
            addHeader();
            addForm();
        }
    }

    private void addHeader() {
        root.addComponent(new Label("Stwórz nowe konto"));
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();

        TextField name = new TextField("Imię");
        TextField email = new TextField("Email");
        Button add = new Button("Stwórz");

        add.addClickListener((clickEvent) -> {
            facade.create(new CreateUserDto(name.getValue(), email.getValue()));
            NotificationService.showHumanizedNotification("Gratulacje! Udało Ci się stworzyć nowe konto!");
        });

        VaadinSession.getCurrent().setErrorHandler((handler) ->
            NotificationService.showErrorNotification(ParameterizedException.label)
        );

        formLayout.addComponents(
                name,
                email,
                add
        );

        root.addComponent(formLayout);
    }

    private void authorized() {
        if (GlobalAuthorizationEntryPoint.isAuthorized()) {
            NotificationService.showErrorNotification("Tylko niezalogowani użytkownicy mogą stworzyć nowe konto");
            root.addComponents(new Label("Błąd 403: Odmowa dostępu"));
        }
    }

    private void addHomepageHref() {
        Button button = new Button("Idź do strony głównej");

        button.setStyleName("link");
        button.addClickListener(e -> getUI().getNavigator().navigateTo("homepage"));

        root.addComponent(button);
    }
}
