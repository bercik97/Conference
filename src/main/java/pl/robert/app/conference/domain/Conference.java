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
import javax.persistence.FetchType;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.shared.QueryConverter;
import pl.robert.app.user.domain.query.UserQueryDto;

import java.util.Set;

import static pl.robert.app.shared.Constants.Conference.COL_LENGTH_NAME;
import static pl.robert.app.shared.Constants.Conference.COL_LENGTH_DETAILS;

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

    @Column(length = COL_LENGTH_NAME, unique = true, nullable = false)
    String name;

    @Column(length = COL_LENGTH_DETAILS, nullable = false)
    String details;

    @OneToMany(
            mappedBy = "conference",
            cascade = CascadeType.REMOVE
    )
    Set<UserQueryDto> users;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "conference",
            cascade = CascadeType.REMOVE
    )
    Set<LectureQueryDto> lectures;

    @Override
    public ConferenceQueryDto query() {
        return new ConferenceQueryDto(id, name, details, users, lectures);
    }
}
