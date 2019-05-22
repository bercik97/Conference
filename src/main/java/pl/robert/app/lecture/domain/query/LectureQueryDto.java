package pl.robert.app.lecture.domain.query;

import java.util.Set;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import pl.robert.app.lecture.domain.LectureType;
import pl.robert.app.shared.BaseQuery;
import pl.robert.app.user.domain.query.UserQueryDto;
import pl.robert.app.conference.domain.query.ConferenceQueryDto;

@Entity
@Table(name = "lecture")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class LectureQueryDto extends BaseQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String lecturer;

    @Enumerated(EnumType.STRING)
    LectureType type;

    String day;

    String time;

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

    public LectureQueryDto(String uuid,
                           Long id,
                           String name,
                           String lecturer,
                           LectureType type,
                           String day,
                           String time,
                           Integer numberOfPlaces,
                           ConferenceQueryDto conference,
                           Set<UserQueryDto> users) {
        super(uuid);
        this.id = id;
        this.name = name;
        this.lecturer = lecturer;
        this.type = type;
        this.day = day;
        this.time = time;
        this.numberOfPlaces = numberOfPlaces;
        this.conference = conference;
        this.users = users;
    }
}
