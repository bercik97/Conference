package pl.robert.app.lecture.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import pl.robert.app.lecture.domain.exception.InvalidLectureException;

import org.apache.logging.log4j.util.Strings;

import static pl.robert.app.shared.Constants.Lecture.LECTURE_ID_FORMAT_REGEX;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class LectureValidator {

    LectureRepository repository;
    LectureService service;

    void checkSubscribeData(String lectureId, Long userId) {
        validateRequiredData(lectureId);
        validateOtherSubscribeData(Long.parseLong(lectureId), userId);
    }

    private void validateOtherSubscribeData(Long lectureId, Long userId) {
        InvalidLectureException.CAUSE cause = null;

        Lecture lecture = repository.findLectureById(lectureId);

        if (lecture.getUsers().size() == lecture.getNumberOfPlaces()) {
            cause = InvalidLectureException.CAUSE.FULL;
        } else if (service.findTermsOfAlreadySubscribedLectures(userId).contains(lecture.getDay() + lecture.getTime())) {
            cause = InvalidLectureException.CAUSE.SUBSCRIBED;
        }

        if (cause != null) {
            throw new InvalidLectureException(cause);
        }
    }

    void checkUnsubscribeData(String lectureId, Long userId) {
        validateRequiredData(lectureId);
        validateOtherUnsubscribeData(Long.parseLong(lectureId), userId);
    }

    private void validateOtherUnsubscribeData(Long lectureId, Long userId) {
        InvalidLectureException.CAUSE cause = null;

        if (!repository.findAlreadySubscribedLecturesByUserId(userId).contains(lectureId)) {
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
        } else if (repository.findLectureById(Long.parseLong(lectureId)) == null) {
            cause = InvalidLectureException.CAUSE.NOT_EXISTS;
        }

        if (cause != null) {
            throw new InvalidLectureException(cause);
        }
    }
}
