package pl.robert.conference.views;

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

import pl.robert.conference.user.domain.UserFacade;
import pl.robert.conference.user.domain.dto.CreateUserDto;

@SpringView(name = "create-account")
@FieldDefaults(level = AccessLevel.PRIVATE)
class CreateAccountView extends Composite implements View {

    UserFacade facade;

    VerticalLayout root;

    public CreateAccountView(UserFacade facade) {
        this.facade = facade;

        setupLayout();
        addHeader();
        addForm();
        addHrefs();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
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

    private void addHrefs() {
        Button homepage = new Button("Idź do strony głównej");

        homepage.setStyleName("link");
        homepage.addClickListener(e -> getUI().getNavigator().navigateTo("homepage"));

        root.addComponent(homepage);
    }
}
