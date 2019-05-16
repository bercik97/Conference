package pl.robert.app.conference.domain.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.user.domain.query.UserQueryDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.Set;

@Entity
@Table(name = "conference")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConferenceQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    String details;

    @Column(name = "number_of_available_places")
    String numberOfAvailablePlaces;

    @OneToMany(
            mappedBy = "conference",
            cascade = CascadeType.REMOVE
    )
    Set<UserQueryDto> users;

    @OneToMany(
            mappedBy = "conference",
            cascade = CascadeType.REMOVE
    )
    Set<LectureQueryDto> lectures;
}
