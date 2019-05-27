package pl.robert.app.conference.domain;

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
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import pl.robert.app.shared.BaseEntity;
import pl.robert.app.shared.QueryConverter;
import pl.robert.app.user.domain.query.UserQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.conference.domain.query.ConferenceQueryDto;

import static pl.robert.app.shared.Constants.Conference.COL_LENGTH_NAME;
import static pl.robert.app.shared.Constants.Conference.COL_LENGTH_DETAILS;

@Entity
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
class Conference extends BaseEntity implements QueryConverter<ConferenceQueryDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true, length = COL_LENGTH_NAME)
    String name;

    @Column(nullable = false, length = COL_LENGTH_DETAILS)
    String details;

    @OneToMany(mappedBy = "conference")
    Set<UserQueryDto> users;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "conference"
    )
    Set<LectureQueryDto> lectures;

    @Override
    public ConferenceQueryDto query() {
        return new ConferenceQueryDto(getUuid(), id, name, details, users, lectures);
    }
}
