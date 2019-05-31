package pl.robert.app.lecture.domain;

import java.util.Set;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;

import pl.robert.app.user.domain.query.UserQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.lecture.domain.query.LectureSchemaQueryDto;
import pl.robert.app.lecture.domain.query.SubscribeLectureQueryDto;
import pl.robert.app.lecture.domain.query.AlreadySubscribedLectureQueryDto;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class LectureFacade {

    LectureService service;
    LectureValidator validator;

    public List<LectureSchemaQueryDto> transformIntoLecturesSchema(Set<LectureQueryDto> lectures) {
        return service.transformIntoLecturesSchema(lectures);
    }

    public List<SubscribeLectureQueryDto> transformIntoSubscribeLecturesSchema(Set<LectureQueryDto> lectures) {
        return service.transformIntoSubscribeLecturesSchema(lectures);
    }

    public List<AlreadySubscribedLectureQueryDto> findAlreadySubscribedLectures(Long userId) {
        return service.findAlreadySubscribedLectures(userId);
    }

    public String findIdsOfAlreadySubscribedLectures(Long userId) {
        return service.findIdsOfAlreadySubscribedLectures(userId);
    }

    public void subscribeLecture(String lectureId, UserQueryDto dto) {
        validator.checkSubscribeData(lectureId, dto.getId());
        service.subscribeLecture(Long.parseLong(lectureId), dto);
    }

    public void unsubscribeLecture(String lectureId, UserQueryDto dto) {
        validator.checkUnsubscribeData(lectureId, dto.getId());
        service.unsubscribeLecture(Long.parseLong(lectureId), dto);
    }
}
