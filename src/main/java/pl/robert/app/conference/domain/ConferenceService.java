package pl.robert.app.conference.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;
import pl.robert.app.conference.domain.exception.ConferenceNotFoundException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
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
