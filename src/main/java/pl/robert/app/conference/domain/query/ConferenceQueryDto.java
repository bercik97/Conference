package pl.robert.app.conference.domain.query;

import java.util.Set;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import pl.robert.app.shared.BaseQuery;
import pl.robert.app.user.domain.query.UserQueryDto;
import pl.robert.app.lecture.domain.query.LectureQueryDto;

@Entity
@Table(name = "conference")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ConferenceQueryDto extends BaseQuery {

    String name;

    String details;

    @OneToMany(mappedBy = "conference")
    Set<UserQueryDto> users;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "conference"
    )
    Set<LectureQueryDto> lectures;

    public ConferenceQueryDto(Long id, String uuid,
                              String name,
                              String details,
                              Set<UserQueryDto> users,
                              Set<LectureQueryDto> lectures) {
        super(id, uuid);
        this.name = name;
        this.details = details;
        this.users = users;
        this.lectures = lectures;
    }
}
