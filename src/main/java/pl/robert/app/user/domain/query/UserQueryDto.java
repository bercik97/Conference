package pl.robert.app.user.domain.query;

import java.util.Set;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import pl.robert.app.shared.BaseQuery;
import pl.robert.app.lecture.domain.query.LectureQueryDto;
import pl.robert.app.conference.domain.query.ConferenceQueryDto;

@Entity
@Table(name = "users")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserQueryDto extends BaseQuery {

    String name;

    String email;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    ConferenceQueryDto conference;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_lecture",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    Set<LectureQueryDto> lectures;

    public UserQueryDto(Long id, String uuid,
                        String name,
                        String email,
                        ConferenceQueryDto conference,
                        Set<LectureQueryDto> lectures) {
        super(id, uuid);
        this.name = name;
        this.email = email;
        this.conference = conference;
        this.lectures = lectures;
    }
}
