package pl.robert.app.lecture.domain;

import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.lecture.domain.query.LectureSchemaQueryDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class LectureService {

    List<LectureSchemaQueryDto> transform(Set<LectureQueryDto> lectures) {
        List<LectureQueryDto> sortedLectures = lectures
                .stream()
                .sorted(Comparator.comparing(LectureQueryDto::getId))
                .collect(Collectors.toList());

        List<LectureSchemaQueryDto> schema = new ArrayList<>(lectures.size() / 3);

        for (int i = 0; i < (lectures.size() + 1) / 3; i++) {
            schema.add(new LectureSchemaQueryDto(
                    sortedLectures.get(i * 3).getDay() + " / " +
                            sortedLectures.get(i * 3).getTime(),
                    sortedLectures.get(i * 3).getName() + " - " +
                            sortedLectures.get(i * 3).getLecturer(),
                    sortedLectures.get(i * 3 + 1).getName() + " - " +
                            sortedLectures.get(i * 3 + 1).getLecturer(),
                    sortedLectures.get(i * 3 + 2).getName() + " - " +
                            sortedLectures.get(i * 3 + 2).getLecturer()
                    )
            );

            if (i % 2 == 0) {
                schema.add(new LectureSchemaQueryDto(
                        sortedLectures.get(i * 3).getDay() + " / " + "11:45-12:00",
                        "Przerwa kawowa",
                        "Przerwa kawowa",
                        "Przerwa kawowa"
                        )
                );
            }
        }

        return schema;
    }
}
