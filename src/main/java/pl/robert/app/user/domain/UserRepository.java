package pl.robert.app.user.domain;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<User, Long> {

    void save(User user);

    Optional<User> findUserByName(String name);

    Set<User> findAll();
}
