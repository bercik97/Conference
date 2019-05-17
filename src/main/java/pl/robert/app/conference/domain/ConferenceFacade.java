package pl.robert.app.conference.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConferenceFacade {

    ConferenceService service;

    public ConferenceQueryDto find() {
        return service.find();
    }
}
