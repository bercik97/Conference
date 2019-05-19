package pl.robert.app.lecture.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.shared.QueryConverter;
import pl.robert.app.user.domain.query.UserQueryDto;

import java.util.Set;

import static pl.robert.app.shared.Constants.Lecture.COL_LENGTH_NAME;
import static pl.robert.app.shared.Constants.Lecture.COL_LENGTH_LECTURER;
import static pl.robert.app.shared.Constants.Lecture.COL_LENGTH_TYPE;
import static pl.robert.app.shared.Constants.Lecture.COL_LENGTH_DAY;
import static pl.robert.app.shared.Constants.Lecture.COL_LENGTH_TIME;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
class Lecture implements QueryConverter<LectureQueryDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = COL_LENGTH_NAME, unique = true, nullable = false)
    String name;

    @Column(length = COL_LENGTH_LECTURER, nullable = false)
    String lecturer;

    @Enumerated(EnumType.STRING)
    @Column(length = COL_LENGTH_TYPE, nullable = false)
    LectureType type;

    @Column(length = COL_LENGTH_DAY, nullable = false)
    String day;

    @Column(length = COL_LENGTH_TIME, nullable = false)
    String time;

    @Column(name = "number_of_places", nullable = false)
    Integer numberOfPlaces;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    ConferenceQueryDto conference;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_lectures",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<UserQueryDto> users;

    @Override
    public LectureQueryDto query() {
        return new LectureQueryDto(id, name, lecturer, type, day, time, numberOfPlaces, conference, users);
    }
}
