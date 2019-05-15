package pl.robert.conference.user.domain.dto;

import lombok.Getter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserDto {

    long id;
    String name;
    String email;
}
