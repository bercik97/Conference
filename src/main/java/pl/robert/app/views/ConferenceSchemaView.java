package pl.robert.app.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Composite;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Button;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import pl.robert.app.conference.domain.ConferenceFacade;
import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.lecture.domain.LectureFacade;
import pl.robert.app.lecture.domain.query.LectureSchemaQueryDto;

import java.util.List;

@SpringView(name = "conference-schema")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ConferenceSchemaView extends Composite implements View {

    LectureFacade lectureFacade;
    ConferenceQueryDto dto;

    VerticalLayout root;

    public ConferenceSchemaView(LectureFacade lectureFacade,
                                ConferenceFacade conferenceFacade) {
        this.lectureFacade = lectureFacade;
        dto = conferenceFacade.find();

        setupLayout();
        addHeader();
        addSchema();
        addHomepageHref();
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

    private void addSchema() {
        List<LectureSchemaQueryDto> lectureSchema = lectureFacade.transformIntoLecturesSchema(dto.getLectures());

        Grid<LectureSchemaQueryDto> grid = new Grid<>();
        grid.setSizeFull();

        grid.setItems(lectureSchema);
        grid.addColumn(LectureSchemaQueryDto::getTerm).setCaption("Dzień / Godzina");
        grid.addColumn(LectureSchemaQueryDto::getInspirationLectureDetails).setCaption("Ścieżka inspiracji");
        grid.addColumn(LectureSchemaQueryDto::getTechnologyLectureDetails).setCaption("Ścieżka technologii");
        grid.addColumn(LectureSchemaQueryDto::getKnowledgeLectureDetails).setCaption("Ścieżka wiedzy");

        root.addComponent(grid);
    }

    private void addHomepageHref() {
        Button button = new Button("Idź do strony głównej");
        button.setStyleName("link");
        button.addClickListener(e -> getUI().getNavigator().navigateTo("homepage"));

        root.addComponent(button);
    }
}
