package pl.robert.app.conference.domain.query;

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
import javax.persistence.OneToMany;
import javax.persistence.FetchType;

import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.user.domain.query.UserQueryDto;

import java.util.Set;

@Entity
@Table(name = "conference")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConferenceQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    String details;

    @OneToMany(mappedBy = "conference")
    Set<UserQueryDto> users;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "conference")
    Set<LectureQueryDto> lectures;
}
