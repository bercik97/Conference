package pl.robert.app.lecture.domain;

import com.vaadin.ui.UI;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import pl.robert.app.lecture.domain.query.AlreadySubscribedLectureQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.lecture.domain.query.LectureSchemaQueryDto;
import pl.robert.app.lecture.domain.query.SubscribeLectureQueryDto;
import pl.robert.app.shared.NotificationService;
import pl.robert.app.shared.SendEmailService;
import pl.robert.app.user.domain.UserFacade;
import pl.robert.app.user.domain.query.UserQueryDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Comparator;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class LectureService {

    LectureRepository repository;
    UserFacade userFacade;

    List<LectureSchemaQueryDto> transformIntoLecturesSchema(Set<LectureQueryDto> lectures) {
        List<LectureQueryDto> sortedLectures = prepareSet(lectures);

        List<LectureSchemaQueryDto> schema = new ArrayList<>(lectures.size() / 3);

        for (int i = 0; i < (lectures.size() + 1) / 3; i++) {
            schema.add(new LectureSchemaQueryDto(
                            sortedLectures.get(i * 3).getDay() + " / " +
                                    sortedLectures.get(i * 3).getTime(),
                            sortedLectures.get(i * 3).getName() + " - " +
                                    sortedLectures.get(i * 3).getLecturer(),
                            sortedLectures.get(i * 3 + 1).getName() + " - " +
                                    sortedLectures.get(i * 3 + 1).getLecturer(),
                            sortedLectures.get(i * 3 + 2).getName() + " - " +
                                    sortedLectures.get(i * 3 + 2).getLecturer()
                    )
            );

            if (i % 2 == 0) {
                schema.add(new LectureSchemaQueryDto(
                                sortedLectures.get(i * 3).getDay() + " / " + "11:45-12:00",
                                "Przerwa kawowa",
                                "Przerwa kawowa",
                                "Przerwa kawowa"
                        )
                );
            }
        }

        return schema;
    }

    List<SubscribeLectureQueryDto> transformIntoSubscribeLecturesSchema(Set<LectureQueryDto> lectures) {
        return prepareSet(lectures)
                .stream()
                .map(lecture -> new SubscribeLectureQueryDto(
                        lecture.getId(),
                        lecture.getDay() + " / " + lecture.getTime(),
                        lecture.getType().getType(),
                        lecture.getName(),
                        lecture.getLecturer(),
                        lecture.getNumberOfPlaces() - lecture.getUsers().size()))
                .collect(Collectors.toList());
    }

    private List<LectureQueryDto> prepareSet(Set<LectureQueryDto> lectures) {
        return lectures
                .stream()
                .sorted(Comparator.comparing(LectureQueryDto::getId))
                .collect(Collectors.toList());
    }

    List<AlreadySubscribedLectureQueryDto> findAlreadySubscribedLectures() {
        return repository.findAlreadySubscribedLecturesByUsername(userFacade.read().getId())
                .stream()
                .map(repository::findLectureById)
                .collect(Collectors.toList())
                .stream()
                .map(lecture -> new AlreadySubscribedLectureQueryDto(
                        lecture.getId(),
                        lecture.getName()
                ))
                .collect(Collectors.toList());
    }

    String findIdsOfAlreadySubscribedLectures() {
        return repository.findAlreadySubscribedLecturesByUsername(userFacade.read().getId())
                .stream()
                .sorted()
                .map(id -> id.toString() + ", ")
                .reduce("", String::concat);
    }

    void subscribeLecture(Long lectureId) {
        Lecture lecture = repository.findLectureById(lectureId);
        UserQueryDto dto = userFacade.read();

        lecture.getUsers().add(dto);

        repository.save(lecture);

        SendEmailService.sendSubscribeEmail(dto.getEmail(), dto.getName(), lectureId);

        NotificationService.showHumanizedNotification("Zapisałeś się na prelekcje o identyfikatorze: " + lectureId);

        UI.getCurrent().getNavigator().navigateTo("subscribe-lectures");
    }

    void unsubscribeLecture(Long lectureId) {
        Lecture lecture = repository.findLectureById(lectureId);
        UserQueryDto dto = userFacade.read();

        lecture.getUsers().remove(dto);

        repository.deleteLectureByUserId(dto.getId(), lectureId);

        SendEmailService.sendSubscribeEmail(dto.getEmail(), dto.getName(), lectureId);

        NotificationService.showHumanizedNotification("Wypisałeś się z prelekcji o identyfikatorze: " + lectureId);

        UI.getCurrent().getNavigator().navigateTo("subscribe-lectures");
    }
}
