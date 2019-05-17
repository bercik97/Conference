package pl.robert.app.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import pl.robert.app.conference.domain.ConferenceFacade;
import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringView(name = "conference-schema")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ConferenceSchemaView extends Composite implements View {

    ConferenceQueryDto dto;

    VerticalLayout root;

    public ConferenceSchemaView(ConferenceFacade facade) {
        dto = facade.find();

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
        List<LectureQueryDto> lectures = dto.getLectures()
                .stream()
                .sorted(Comparator.comparing(LectureQueryDto::getId))
                .collect(Collectors.toList());

        List<Schema> schemat = new ArrayList<>();

        for (int i = 0; i < (lectures.size() + 1) / 3; i++) {
            schemat.add(new Schema(
                    lectures.get(i * 3).getDay() + " / " + lectures.get(i * 3).getTime(),
                    lectures.get(i * 3).getName() + " - " + lectures.get(i * 3).getLecturer(),
                    lectures.get(i * 3 + 1).getName() + " - " + lectures.get(i * 3 + 1).getLecturer(),
                    lectures.get(i * 3 + 2).getName() + " - " + lectures.get(i * 3 + 2).getLecturer()
            ));

            if (i % 2 == 0) {
                schemat.add(new Schema(
                        lectures.get(i * 3).getDay() + " / " + "11:45 - 12:00",
                        "Przerwa kawowa",
                        "Przerwa kawowa",
                        "Przerwa kawowa"
                ));
            }
        }

        Grid<Schema> grid = new Grid<>();
        grid.setSizeFull();

        grid.setItems(schemat);
        grid.addColumn(Schema::getTerm).setCaption("Dzien / Godzina");
        grid.addColumn(Schema::getInspirationLectureDetails).setCaption("Ścieżka inspiracji");
        grid.addColumn(Schema::getTechnologyLectureDetails).setCaption("Ścieżka technologii");
        grid.addColumn(Schema::getKnowledgeLectureDetails).setCaption("Ścieżka wiedzy");

        root.addComponent(grid);
    }

    private void addHomepageHref() {
        Button homepage = new Button("Idź do strony głównej");
        homepage.setStyleName("link");
        homepage.addClickListener(e -> getUI().getNavigator().navigateTo("homepage"));

        root.addComponent(homepage);
    }
}
