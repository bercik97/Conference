package pl.robert.app.conference.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ConferenceFacade {

    ConferenceService service;

    public ConferenceQueryDto find() {
        return service.find();
    }
}
