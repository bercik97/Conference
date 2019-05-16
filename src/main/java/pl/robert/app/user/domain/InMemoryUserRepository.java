package pl.robert.app.user.domain;

import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import pl.robert.app.user.domain.dto.CreateUserDto;
import pl.robert.app.user.domain.exception.UserNotFoundException;
import pl.robert.app.user.domain.query.UserQueryDto;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
class InMemoryUserRepository {

    ConcurrentHashMap<Long, User> map = new ConcurrentHashMap<>();

    void create(Long id, CreateUserDto dto) {
        map.put(id, UserFactory.create(dto));
    }

    UserQueryDto read(Long id) {
        User User = map.get(id);
        if (User == null) {
            throw new UserNotFoundException(id);
        }
        return map.get(id).query();
    }

    void update(Long id, String name, String email) {
        if (isUserIdNotNull(id)) {
            map.put(id, User.builder()
                    .id(id)
                    .name(name)
                    .email(email)
                    .build());
        }
    }

    void delete(Long id) {
        if (isUserIdNotNull(id)) {
            map.remove(id);
        }
    }

    Page<UserQueryDto> readAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size()).map(User::query);
    }

    private boolean isUserIdNotNull(Long id) {
        if (map.get(id) == null) {
            throw new UserNotFoundException(id);
        }
        return true;
    }
}
