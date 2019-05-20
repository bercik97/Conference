package pl.robert.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import pl.robert.app.user.domain.dto.CreateUserDto;
import pl.robert.app.conference.domain.ConferenceFacade;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class UserFactory {

    ConferenceFacade facade;

    User create(CreateUserDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .conference(facade.find())
                .build();
    }
}
