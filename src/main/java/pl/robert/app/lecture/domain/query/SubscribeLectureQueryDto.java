package pl.robert.app.lecture.domain.query;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class SubscribeLectureQueryDto {

    Long id;
    String term;
    String type;
    String name;
    String lecturer;
    Integer leftPlaces;
}
