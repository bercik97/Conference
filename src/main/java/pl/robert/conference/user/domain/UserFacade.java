package pl.robert.conference.user.domain;

import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.robert.conference.user.domain.dto.CreateUserDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

    InMemoryUserRepository repository;

    public void create(CreateUserDto dto) {
        repository.create(UserFactory.create(dto));
    }

    public User read(Long id) {
        return repository.read(id);
    }

    public void update(Long id, String name, String email) {
        repository.update(id, name, email);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public Page<User> readAll(Pageable pageable) {
        return repository.readAll(pageable);
    }
}
