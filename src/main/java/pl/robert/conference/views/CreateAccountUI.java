package pl.robert.conference.views;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.robert.conference.user.domain.UserFacade;
import pl.robert.conference.user.domain.dto.CreateUserDto;

@SpringUI
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountUI extends UI {

    private VerticalLayout root;

    private UserFacade facade;

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
        root.addComponent(new Label("Create new account"));
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();

        TextField name = new TextField();
        TextField email = new TextField();
        Button add = new Button("Create");

        name.setPlaceholder("Enter your name");
        email.setPlaceholder("Enter your email");

        formLayout.addComponents(name, email, add);

        add.addClickListener(clickEvent -> facade.create(
                new CreateUserDto(name.getValue(), email.getValue())));

        root.addComponent(formLayout);
    }
}
