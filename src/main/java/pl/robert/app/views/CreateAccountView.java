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

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import pl.robert.app.shared.GlobalAuthorizationEntryPoint;
import pl.robert.app.shared.NotificationService;
import pl.robert.app.user.domain.UserFacade;
import pl.robert.app.user.domain.dto.CreateUserDto;

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
            addHomepageHref();
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

        add.addClickListener(clickEvent -> facade.create(
                new CreateUserDto(name.getValue(), email.getValue())));

        formLayout.addComponents(name, email, add);

        root.addComponent(formLayout);
    }

    private void authorized() {
        if (GlobalAuthorizationEntryPoint.isAuthorized()) {
            NotificationService.showErrorNotification("Tylko niezalogowani użytkownicy mogą stworzyć nowe konto");

            Label label = new Label("Błąd 403: Odmowa dostępu");

            root.addComponents(label);
            addHomepageHref();
        }
    }

    private void addHomepageHref() {
        Button homepage = new Button("Idź do strony głównej");
        homepage.setStyleName("link");
        homepage.addClickListener(e -> getUI().getNavigator().navigateTo("homepage"));

        root.addComponent(homepage);
    }
}
