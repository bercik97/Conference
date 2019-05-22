package pl.robert.app.conference.domain.query;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import pl.robert.app.shared.BaseQuery;
import pl.robert.app.user.domain.query.UserQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;

@Entity
@Table(name = "conference")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceQueryDto extends BaseQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String details;

    @OneToMany(mappedBy = "conference")
    Set<UserQueryDto> users;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "conference"
    )
    Set<LectureQueryDto> lectures;

    public ConferenceQueryDto(String uuid,
                              Long id,
                              String name,
                              String details,
                              Set<UserQueryDto> users,
                              Set<LectureQueryDto> lectures) {
        super(uuid);
        this.id = id;
        this.name = name;
        this.details = details;
        this.users = users;
        this.lectures = lectures;
    }
}
