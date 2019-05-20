package pl.robert.app.lecture.domain;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum LectureType {

    INSPIRATION("Ścieżka inspiracji"),
    TECHNOLOGY("Ścieżka technologii"),
    KNOWLEDGE("Ścieżka wiedzy");

    String type;
}
