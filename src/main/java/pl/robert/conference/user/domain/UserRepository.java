package pl.robert.conference.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findUserByName(String name);
}
