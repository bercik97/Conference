package pl.robert.app.lecture.domain;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum LectureType {

    INSPIRATION("Ścieżka inspiracji"),
    TECHNOLOGY("Ścieżka technologii"),
    KNOWLEDGE("Ścieżka wiedzy");

    String type;
}
