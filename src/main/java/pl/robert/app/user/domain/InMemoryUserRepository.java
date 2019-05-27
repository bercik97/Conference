package pl.robert.app.user.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
class InMemoryUserRepository implements UserRepository {

    ConcurrentHashMap<String, User> map = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        map.put(user.getName(), user);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return Optional.of(map.get(name));
    }

    @Override
    public Set<User> findAll() {
        return new HashSet<>(map.values());
    }
}
