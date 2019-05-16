package pl.robert.app.lecture.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.shared.QueryConverter;

import javax.persistence.*;

@Entity
@Table(name = "lecture")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Lecture implements QueryConverter<LectureQueryDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    String lecturer;

    @Enumerated(EnumType.STRING)
    LectureType type;

    String day;

    @Column(name = "start_time")
    String startTime;

    @Column(name = "number_of_places")
    String numberOfPlaces;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    ConferenceQueryDto conference;

    @Override
    public LectureQueryDto query() {
        return new LectureQueryDto(id, name, lecturer, type, day, startTime, numberOfPlaces, conference);
    }
}
