package pl.robert.app.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Composite;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Label;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import pl.robert.app.conference.domain.ConferenceFacade;
import pl.robert.app.conference.domain.query.ConferenceQueryDto;

@SpringView(name = "conference-schema")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ConferenceSchemaView extends Composite implements View {

    ConferenceQueryDto dto;

    VerticalLayout root;

    public ConferenceSchemaView(ConferenceFacade facade) {
        dto = facade.find();

        setupLayout();
        addHeader();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void addHeader() {
        Label name = new Label(dto.getName());
        Label details = new Label(dto.getDetails());
        Label numberOfAvailablePlaces = new Label("Liczba wolnych miejsc: " + dto.getNumberOfAvailablePlaces());

        root.addComponents(
                name,
                details,
                numberOfAvailablePlaces
        );
    }
}
