package pl.robert.conference.user.domain;

import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.robert.conference.user.domain.dto.CreateUserDto;
import pl.robert.conference.user.domain.dto.UserDto;

import javax.transaction.Transactional;

@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

    UserRepository repository;

    public void create(CreateUserDto dto) {
        repository.save(UserFactory.create(dto));
    }

    public UserDto read(Long id) {
        return repository.findUserById(id).dto();
    }

    public void update(Long id, String email) {
        repository.findUserById(id).setEmail(email);
    }

    public void delete(Long id) {
        repository.delete(repository.findUserById(id));
    }

    public Page<UserDto> readAll(Pageable pageable) {
        return repository.findAll(pageable).map(User::dto);
    }
}
