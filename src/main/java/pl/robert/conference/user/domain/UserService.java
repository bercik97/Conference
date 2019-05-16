package pl.robert.conference.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import com.vaadin.ui.Notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.robert.conference.user.domain.dto.CreateUserDto;
import pl.robert.conference.user.domain.dto.UserDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserService {

    UserRepository repository;

    void create(CreateUserDto dto) {
        repository.save(UserFactory.create(dto));
        Notification.show("Gratulacje! Udało Ci się stworzyć konto!");
    }

    UserDto read(Long id) {
        return repository.findUserById(id).dto();
    }

    void update(Long id, String email) {
        repository.findUserById(id).setEmail(email);
    }

    void delete(Long id) {
        repository.delete(repository.findUserById(id));
    }

    Page<UserDto> readAll(Pageable pageable) {
        return repository.findAll(pageable).map(User::dto);
    }

}
