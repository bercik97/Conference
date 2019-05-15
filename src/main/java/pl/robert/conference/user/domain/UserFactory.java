package pl.robert.conference.user.domain;

import pl.robert.conference.user.domain.dto.CreateUserDto;

class UserFactory {

    static User create(CreateUserDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
}
