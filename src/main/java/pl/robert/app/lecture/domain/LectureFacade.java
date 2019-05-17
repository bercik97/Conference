package pl.robert.app.lecture.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.lecture.domain.query.LectureSchemaQueryDto;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LectureFacade {

    LectureService service;

    public List<LectureSchemaQueryDto> transform(Set<LectureQueryDto> lectures) {
        return service.transform(lectures);
    }
}
