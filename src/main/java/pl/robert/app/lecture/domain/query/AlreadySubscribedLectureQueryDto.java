package pl.robert.app.lecture.domain.query;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class AlreadySubscribedLectureQueryDto {

    Long id;
    String name;
}
