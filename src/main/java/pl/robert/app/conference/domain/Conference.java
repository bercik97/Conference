package pl.robert.app.conference.domain;

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
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.shared.QueryConverter;
import pl.robert.app.user.domain.query.UserQueryDto;

import java.util.Set;

@Entity
@Table(name = "conference")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Conference implements QueryConverter<ConferenceQueryDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String details;

    @Column(name = "number_of_available_places", nullable = false)
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

    @Override
    public ConferenceQueryDto query() {
        return new ConferenceQueryDto(id, name, details, numberOfAvailablePlaces, users, lectures);
    }
}
