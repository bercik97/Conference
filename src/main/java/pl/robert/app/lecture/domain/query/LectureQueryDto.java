package pl.robert.app.lecture.domain.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.lecture.domain.LectureType;

import javax.persistence.*;

@Entity
@Table(name = "lecture")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LectureQueryDto {

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
    String numberOfAvailablePlaces;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    ConferenceQueryDto conference;
}
