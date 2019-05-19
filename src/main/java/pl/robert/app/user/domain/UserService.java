package pl.robert.app.user.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import pl.robert.app.shared.NotificationService;
import pl.robert.app.user.domain.dto.CreateUserDto;
import pl.robert.app.user.domain.exception.InvalidUserException;
import pl.robert.app.user.domain.query.UserQueryDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserService {

    UserRepository repository;

    void create(CreateUserDto dto) {
        repository.save(UserFactory.create(dto));
        NotificationService.showHumanizedNotification("Gratulacje! Udało Ci się stworzyć nowe konto!");
    }

    long findIdByName(String name) {
        return repository.findUserByName(name)
                .map(User::getId)
                .orElseThrow(() -> new InvalidUserException(InvalidUserException.CAUSE.NAME_NOT_EXISTS));
    }

    UserQueryDto read(String name) {
        return repository.findUserByName(name)
                .map(User::query)
                .orElseThrow(() -> new InvalidUserException(InvalidUserException.CAUSE.NAME_NOT_EXISTS));
    }

    void update(Long id, String email) {
        repository.findUserById(id).setEmail(email);
        NotificationService.showHumanizedNotification("Zmieniłeś swój email!");
    }
}
