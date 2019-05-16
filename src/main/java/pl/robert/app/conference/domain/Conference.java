package pl.robert.app.conference.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.shared.QueryConverter;
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
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Conference implements QueryConverter<ConferenceQueryDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    String spot;

    @Column(name = "number_of_available_places")
    String numberOfAvailablePlaces;

    @OneToMany(
            mappedBy = "conference",
            cascade = CascadeType.REMOVE
    )
    Set<UserQueryDto> users;

    @Override
    public ConferenceQueryDto query() {
        return new ConferenceQueryDto(id, name, spot, numberOfAvailablePlaces, users);
    }
}
