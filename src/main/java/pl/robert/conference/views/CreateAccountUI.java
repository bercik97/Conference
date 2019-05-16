package pl.robert.conference.views;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button;

import pl.robert.conference.user.domain.UserFacade;
import pl.robert.conference.user.domain.dto.CreateUserDto;

@SpringUI
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountUI extends UI {

    VerticalLayout root;

    UserFacade facade;

    public CreateAccountUI(UserFacade facade) {
        this.facade = facade;
    }

    @Override
    protected void init(VaadinRequest request) {
        setupLayout();
        addHeader();
        addForm();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(root);
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
}
