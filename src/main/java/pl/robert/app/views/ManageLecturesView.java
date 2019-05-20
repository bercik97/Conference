package pl.robert.app.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import pl.robert.app.conference.domain.ConferenceFacade;
import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.lecture.domain.LectureFacade;
import pl.robert.app.lecture.domain.query.SubscribeLectureQueryDto;
import pl.robert.app.shared.GlobalAuthorizationEntryPoint;
import pl.robert.app.shared.NotificationService;
import pl.robert.app.shared.SendEmailService;
import pl.robert.app.user.domain.query.UserQueryDto;

import java.util.List;

@SpringView(name = "manage-lectures")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ManageLecturesView extends Composite implements View {

    LectureFacade lectureFacade;
    ConferenceQueryDto dto;

    VerticalLayout root;

    public ManageLecturesView(LectureFacade lectureFacade,
                              ConferenceFacade conferenceFacade) {
        this.lectureFacade = lectureFacade;
        dto = conferenceFacade.find();

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
            NotificationService.showErrorNotification("Tylko zalogowani użytkownicy mogą zapisać się na prelekcje");
            root.addComponents(new Label("Błąd 403: Odmowa dostępu"));
        }
    }

    private void authorized() {
        if (GlobalAuthorizationEntryPoint.isAuthorized()) {
            addHeader();
            addSchema();
            addManageLectures();
            addIdsOfAlreadySubscribedLectures();
        }
    }

    private void addHeader() {
        root.addComponent(new Label("Poniżej znajduje się lista wszystkich prelekcji"));
    }

    private void addSchema() {
        List<SubscribeLectureQueryDto> subscribeLecturesSchema =
                lectureFacade.transformIntoSubscribeLecturesSchema(dto.getLectures());

        Grid<SubscribeLectureQueryDto> grid = new Grid<>();

        grid.setSizeFull();
        grid.setItems(subscribeLecturesSchema);

        grid.addColumn(SubscribeLectureQueryDto::getId).setCaption("Identyfikator");
        grid.addColumn(SubscribeLectureQueryDto::getTerm).setCaption("Dzień / Godzina");
        grid.addColumn(SubscribeLectureQueryDto::getType).setCaption("Ścieżka");
        grid.addColumn(SubscribeLectureQueryDto::getName).setCaption("Nazwa");
        grid.addColumn(SubscribeLectureQueryDto::getLecturer).setCaption("Prelegent");
        grid.addColumn(SubscribeLectureQueryDto::getLeftPlaces).setCaption("Liczba wolnych miejsc");

        root.addComponent(grid);
    }

    private void addManageLectures() {
        HorizontalLayout layout = new HorizontalLayout();

        TextField lectureId = new TextField("Identyfikator prelekcji");

        Button subscribe = new Button("Zapisz się");
        subscribe.addClickListener((clickEvent) -> {
            lectureFacade.subscribeLecture(lectureId.getValue());

            NotificationService.showHumanizedNotification("Zapisałeś się na prelekcje o identyfikatorze: " +
                    lectureId.getValue());

            UI.getCurrent().getNavigator().navigateTo("manage-lectures");
        });

        Button unsubscribe = new Button("Wypisz się");
        unsubscribe.addClickListener((clickEvent) -> {
            lectureFacade.unsubscribeLecture(lectureId.getValue());

            NotificationService.showHumanizedNotification("Wypisałeś się z prelekcji o identyfikatorze: " +
                    lectureId.getValue());

            UI.getCurrent().getNavigator().navigateTo("manage-lectures");
        });

        layout.addComponents(
                lectureId,
                subscribe,
                unsubscribe
        );

        root.addComponent(layout);
    }

    private void addIdsOfAlreadySubscribedLectures() {
        String ids = lectureFacade.findIdsOfAlreadySubscribedLectures();

        Label label = new Label();

        if (ids.isEmpty()) {
            label.setCaption("Lista identyfikatorów prelekcji na które zapisałeś się: Brak");
        } else {
            label.setCaption("Lista identyfikatorów prelekcji na które zapisałeś się: " +
                    ids.substring(0, ids.length() - 2));
        }

        root.addComponent(label);
    }

    private void addHomepageHref() {
        Button button = new Button("Idź do strony głównej");

        button.setStyleName("link");
        button.addClickListener(e -> getUI().getNavigator().navigateTo("homepage"));

        root.addComponent(button);
    }
}
