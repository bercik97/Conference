package pl.robert.app.lecture.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import pl.robert.app.lecture.domain.query.AlreadySubscribedLectureQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.lecture.domain.query.LectureSchemaQueryDto;
import pl.robert.app.lecture.domain.query.SubscribeLectureQueryDto;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LectureFacade {

    LectureService service;
    LectureValidator validator;

    public List<LectureSchemaQueryDto> transformIntoLecturesSchema(Set<LectureQueryDto> lectures) {
        return service.transformIntoLecturesSchema(lectures);
    }

    public List<SubscribeLectureQueryDto> transformIntoSubscribeLecturesSchema(Set<LectureQueryDto> lectures) {
        return service.transformIntoSubscribeLecturesSchema(lectures);
    }

    public List<AlreadySubscribedLectureQueryDto> findAlreadySubscribedLectures() {
        return service.findAlreadySubscribedLectures();
    }

    public String findIdsOfAlreadySubscribedLectures() {
        return service.findIdsOfAlreadySubscribedLectures();
    }

    public void subscribeLecture(String lectureId) {
        validator.checkSubscribeData(lectureId);
        service.subscribeLecture(Long.parseLong(lectureId));
    }

    public void unsubscribeLecture(String lectureId) {
        validator.checkUnsubscribeData(lectureId);
        service.unsubscribeLecture(Long.parseLong(lectureId));
    }
}
