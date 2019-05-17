package pl.robert.app.lecture.domain.query;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class LectureSchemaQueryDto {

    String term;
    String inspirationLectureDetails;
    String technologyLectureDetails;
    String knowledgeLectureDetails;
}
