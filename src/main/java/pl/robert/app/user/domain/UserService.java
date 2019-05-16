package pl.robert.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.robert.app.shared.NotificationService;

import pl.robert.app.user.domain.dto.CreateUserDto;
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
        return repository.findUserByName(name).getId();
    }

    UserQueryDto read(String name) {
        return repository.findUserByName(name).query();
    }

    void update(Long id, String email) {
        repository.findUserById(id).setEmail(email);
        NotificationService.showHumanizedNotification("Zmieniłeś swój email!");
    }

    void delete(Long id) {
        repository.delete(repository.findUserById(id));
    }

    Page<UserQueryDto> readAll(Pageable pageable) {
        return repository.findAll(pageable).map(User::query);
    }
}
