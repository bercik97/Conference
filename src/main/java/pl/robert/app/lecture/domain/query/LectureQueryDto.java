package pl.robert.app.lecture.domain.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;

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
import pl.robert.app.lecture.domain.LectureType;
import pl.robert.app.user.domain.query.UserQueryDto;

import java.util.Set;

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

    String time;

    @Column(name = "number_of_places")
    Integer numberOfPlaces;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    ConferenceQueryDto conference;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_lectures",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<UserQueryDto> users;
}
