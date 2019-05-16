package pl.robert.conference.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.robert.conference.shared.GlobalAuthorizationEntryPoint;
import pl.robert.conference.shared.NotificationService;
import pl.robert.conference.user.domain.UserFacade;

@SpringView(name = "change-email")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeEmailView extends Composite implements View {

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

            Label label = new Label("Błąd 403: Odmowa dostępu");

            root.addComponents(label);
            addHomepageHref();
        }
    }

    private void authorized() {
        if (GlobalAuthorizationEntryPoint.isAuthorized()) {
            addHeader();

            HorizontalLayout formLayout = new HorizontalLayout();

            TextField newEmail = new TextField("Nowy email");
            Button change = new Button("Zmień");

            change.addClickListener(clickEvent -> facade.update(
                    facade.findIdByName(GlobalAuthorizationEntryPoint.name), newEmail.getValue()));

            formLayout.addComponents(newEmail, change);

            root.addComponent(formLayout);
            addHomepageHref();
        }
    }

    private void addHeader() {
        root.addComponent(new Label("Zmień email"));
    }

    private void addHomepageHref() {
        Button homepage = new Button("Idź do strony głównej");
        homepage.setStyleName("link");
        homepage.addClickListener(e -> getUI().getNavigator().navigateTo("homepage"));

        root.addComponent(homepage);
    }
}
