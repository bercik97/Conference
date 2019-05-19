package pl.robert.app.user.domain.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    String email;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    ConferenceQueryDto conference;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_lectures",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    Set<LectureQueryDto> lectures;
}
