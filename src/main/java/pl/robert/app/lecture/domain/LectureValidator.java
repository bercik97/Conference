package pl.robert.app.lecture.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import org.apache.logging.log4j.util.Strings;

import pl.robert.app.lecture.domain.exception.InvalidLectureException;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class LectureValidator {

    LectureRepository repository;

    void checkInputData(String lectureId) {
        validateRequiredData(lectureId);
        validateOtherData(Long.parseLong(lectureId));
    }

    private void validateRequiredData(String lectureId) {
        InvalidLectureException.CAUSE cause = null;

        if (Strings.isBlank(lectureId)) {
            cause = InvalidLectureException.CAUSE.BLANK;
        } else if (!lectureId.matches("\\d+"))  {
            cause = InvalidLectureException.CAUSE.FORMAT;
        }

        if (cause != null) {
            throw new InvalidLectureException(cause);
        }
    }

    private void validateOtherData(Long lectureId) {
        InvalidLectureException.CAUSE cause = null;

        Lecture lecture = repository.findLectureById(lectureId);

        if (lecture.getUsers().size() == lecture.getNumberOfPlaces()) {
            cause = InvalidLectureException.CAUSE.FULL;
        }

        if (cause != null) {
            throw new InvalidLectureException(cause);
        }
    }
}
