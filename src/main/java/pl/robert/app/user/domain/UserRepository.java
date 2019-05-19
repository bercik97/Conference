package pl.robert.app.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    Optional<User> findUserByName(String name);

    Optional<User> findUserByEmail(String email);
}
