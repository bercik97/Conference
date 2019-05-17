package pl.robert.app.lecture.domain;

import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.lecture.domain.query.LectureSchemaQueryDto;
import pl.robert.app.lecture.domain.query.SubscribeLectureQueryDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class LectureService {

    List<LectureSchemaQueryDto> transform(Set<LectureQueryDto> lectures) {
        List<LectureQueryDto> sortedLectures = prepareSet(lectures);

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

    List<SubscribeLectureQueryDto> transformIntoSubscribeLecturesSchema(Set<LectureQueryDto> lectures) {
        return prepareSet(lectures)
                .stream()
                .map(lecture -> new SubscribeLectureQueryDto(
                        lecture.getId(),
                        lecture.getDay() + " / " + lecture.getTime(),
                        lecture.getType().getType(),
                        lecture.getName(),
                        lecture.getLecturer(),
                        lecture.getNumberOfPlaces() - lecture.getUsers().size()))
                .collect(Collectors.toList());
    }

    private List<LectureQueryDto> prepareSet(Set<LectureQueryDto> lectures) {
        return lectures
                .stream()
                .sorted(Comparator.comparing(LectureQueryDto::getId))
                .collect(Collectors.toList());
    }
}
