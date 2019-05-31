package pl.robert.app.conference.domain;

import java.util.Set;

import org.springframework.data.repository.Repository;

interface ConferenceRepository extends Repository<Conference, Long> {

    Set<Conference> findAll();
}
