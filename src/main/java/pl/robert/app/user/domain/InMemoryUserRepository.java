package pl.robert.app.user.domain;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class InMemoryUserRepository implements UserRepository {

    ConcurrentHashMap<String, User> map;

    @Override
    public void save(User user) {
        map.put(user.getName(), user);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return map.entrySet()
                .stream()
                .filter(map -> map.getKey().equals(name))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    @Override
    public Set<User> findAll() {
        return new HashSet<>(map.values());
    }
}
