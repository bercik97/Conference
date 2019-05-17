package pl.robert.app.conference.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
