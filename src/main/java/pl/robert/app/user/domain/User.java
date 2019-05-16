package pl.robert.app.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import static pl.robert.app.user.domain.UserValidator.COL_MAX_LENGTH_NAME;

import pl.robert.app.user.domain.dto.UserDto;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = COL_MAX_LENGTH_NAME, unique = true, nullable = false)
    String name;

    @Column(unique = true, nullable = false)
    String email;

    UserDto dto() {
        return UserDto.builder()
                .name(name)
                .email(email)
                .build();
    }
}

