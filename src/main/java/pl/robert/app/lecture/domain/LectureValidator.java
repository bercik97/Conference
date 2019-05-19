package pl.robert.app.lecture.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import org.apache.logging.log4j.util.Strings;

import pl.robert.app.lecture.domain.exception.InvalidLectureException;
import pl.robert.app.shared.GlobalAuthorizationEntryPoint;
import pl.robert.app.user.domain.UserFacade;

import static pl.robert.app.shared.Constants.Lecture.LECTURE_ID_FORMAT_REGEX;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class LectureValidator {

    LectureRepository repository;
    LectureService service;
    UserFacade userFacade;

    void checkSubscribeData(String lectureId) {
        validateRequiredData(lectureId);
        validateOtherSubscribeData(Long.parseLong(lectureId));
    }

    private void validateOtherSubscribeData(Long lectureId) {
        InvalidLectureException.CAUSE cause = null;

        Lecture lecture = repository.findLectureById(lectureId);

        if (lecture.getUsers().size() == lecture.getNumberOfPlaces()) {
            cause = InvalidLectureException.CAUSE.FULL;
        } else if (service.findTermsOfAlreadySubscribedLectures().contains(lecture.getDay() + lecture.getTime())) {
            cause = InvalidLectureException.CAUSE.SUBSCRIBED;
        }

        if (cause != null) {
            throw new InvalidLectureException(cause);
        }
    }

    void checkUnsubscribeData(String lectureId) {
        validateRequiredData(lectureId);
        validateOtherUnsubscribeData(Long.parseLong(lectureId));
    }

    private void validateOtherUnsubscribeData(Long lectureId) {
        InvalidLectureException.CAUSE cause = null;

        if (!repository.findAlreadySubscribedLecturesByUsername(
             userFacade.findIdByName(GlobalAuthorizationEntryPoint.name)).contains(lectureId)) {
            cause = InvalidLectureException.CAUSE.UNSUBSCRIBED;
        }

        if (cause != null) {
            throw new InvalidLectureException(cause);
        }
    }

    private void validateRequiredData(String lectureId) {
        InvalidLectureException.CAUSE cause = null;

        if (Strings.isBlank(lectureId)) {
            cause = InvalidLectureException.CAUSE.BLANK;
        } else if (!LECTURE_ID_FORMAT_REGEX.matcher(lectureId).find()) {
            cause = InvalidLectureException.CAUSE.FORMAT;
        } else if (!repository.findById(Long.parseLong(lectureId)).isPresent()) {
            cause = InvalidLectureException.CAUSE.NOT_EXISTS;
        }

        if (cause != null) {
            throw new InvalidLectureException(cause);
        }
    }
}
