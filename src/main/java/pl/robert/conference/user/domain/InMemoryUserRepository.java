package pl.robert.conference.user.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.robert.conference.user.domain.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
class InMemoryUserRepository {

    ConcurrentHashMap<Long, User> map = new ConcurrentHashMap<>();

    void create(User user) {
        map.put(user.getId(), user);
    }

    User read(Long id) {
        User User = map.get(id);
        if (User == null) {
            throw new UserNotFoundException(id);
        }
        return map.get(id);
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

    Page<User> readAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    private boolean isUserIdNotNull(Long id) {
        if (map.get(id) == null) {
            throw new UserNotFoundException(id);
        }
        return true;
    }
}
