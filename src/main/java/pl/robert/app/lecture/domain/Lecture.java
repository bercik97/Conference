package pl.robert.app.lecture.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import javax.persistence.Entity;
import javax.persistence.Table;
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

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String lecturer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    LectureType type;

    @Column(nullable = false)
    String day;

    @Column(nullable = false)
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
