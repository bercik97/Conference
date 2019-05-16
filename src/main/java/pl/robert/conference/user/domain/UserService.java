package pl.robert.conference.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.robert.conference.shared.NotificationService;

import pl.robert.conference.user.domain.dto.CreateUserDto;
import pl.robert.conference.user.domain.dto.UserDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserService {

    UserRepository repository;

    void create(CreateUserDto dto) {
        repository.save(UserFactory.create(dto));
        NotificationService.showHumanizedNotification("Gratulacje! Udało Ci się stworzyć nowe konto!");
    }

    long findIdByName(String name) {
        return repository.findUserByName(name).getId();
    }

    UserDto read(Long id) {
        return repository.findUserById(id).dto();
    }

    void update(Long id, String email) {
        repository.findUserById(id).setEmail(email);
        NotificationService.showHumanizedNotification("Zmieniłeś swój email!");
    }

    void delete(Long id) {
        repository.delete(repository.findUserById(id));
    }

    Page<UserDto> readAll(Pageable pageable) {
        return repository.findAll(pageable).map(User::dto);
    }
}
