package pl.robert.app.lecture.domain;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import pl.robert.app.shared.BaseEntity;
import pl.robert.app.shared.QueryConverter;
import pl.robert.app.user.domain.query.UserQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.conference.domain.query.ConferenceQueryDto;

import static pl.robert.app.shared.Constants.Lecture.COL_LENGTH_DAY;
import static pl.robert.app.shared.Constants.Lecture.COL_LENGTH_NAME;
import static pl.robert.app.shared.Constants.Lecture.COL_LENGTH_TYPE;
import static pl.robert.app.shared.Constants.Lecture.COL_LENGTH_TIME;
import static pl.robert.app.shared.Constants.Lecture.COL_LENGTH_LECTURER;

@Entity
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Lecture extends BaseEntity implements QueryConverter<LectureQueryDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true, length = COL_LENGTH_NAME)
    String name;

    @Column(nullable = false, length = COL_LENGTH_LECTURER)
    String lecturer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = COL_LENGTH_TYPE)
    LectureType type;

    @Column(nullable = false, length = COL_LENGTH_DAY)
    String day;

    @Column(nullable = false, length = COL_LENGTH_TIME)
    String time;

    @Column(nullable = false)
    Integer numberOfPlaces;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    ConferenceQueryDto conference;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_lecture",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<UserQueryDto> users;

    @Override
    public LectureQueryDto query() {
        return new LectureQueryDto(getId(), getUuid(), name, lecturer, type, day, time, numberOfPlaces, conference, users);
    }
}
