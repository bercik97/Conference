package pl.robert.app.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import static pl.robert.app.user.domain.UserValidator.COL_MAX_LENGTH_NAME;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.shared.QueryConverter;
import pl.robert.app.user.domain.query.UserQueryDto;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class User implements QueryConverter<UserQueryDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = COL_MAX_LENGTH_NAME, unique = true, nullable = false)
    String name;

    @Column(unique = true, nullable = false)
    String email;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    ConferenceQueryDto conference;

    @Override
    public UserQueryDto query() {
        return new UserQueryDto(id, name, email, conference);
    }
}
