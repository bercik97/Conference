package pl.robert.app.lecture.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import org.apache.logging.log4j.util.Strings;

import pl.robert.app.lecture.domain.exception.InvalidLectureException;

import java.util.regex.Pattern;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class LectureValidator {

    Pattern VALID_LECTURE_ID_FORMAT_REGEX = Pattern.compile("\\d+", Pattern.CASE_INSENSITIVE);

    LectureRepository repository;

    void checkInputData(String lectureId) {
        validateRequiredData(lectureId);
        validateOtherData(Long.parseLong(lectureId));
    }

    private void validateRequiredData(String lectureId) {
        InvalidLectureException.CAUSE cause = null;

        if (Strings.isBlank(lectureId)) {
            cause = InvalidLectureException.CAUSE.BLANK;
        } else if (!VALID_LECTURE_ID_FORMAT_REGEX.matcher(lectureId).find())  {
            cause = InvalidLectureException.CAUSE.FORMAT;
        } else if (!repository.findById(Long.parseLong(lectureId)).isPresent()) {
            cause = InvalidLectureException.CAUSE.NOT_EXISTS;
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
