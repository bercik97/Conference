package pl.robert.app.conference.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import pl.robert.app.conference.domain.exception.ConferenceNotFoundException;
import pl.robert.app.conference.domain.query.ConferenceQueryDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ConferenceService {

    ConferenceRepository repository;

    ConferenceQueryDto find() {
        return repository.findAll()
                .stream()
                .findFirst()
                .map(Conference::query)
                .orElseThrow(ConferenceNotFoundException::new);
    }
}
