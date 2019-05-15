package pl.robert.conference.user.domain.dto;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class UserDto {

    long id;
    String name;
    String email;
}
