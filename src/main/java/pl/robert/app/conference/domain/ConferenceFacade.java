package pl.robert.app.conference.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.cache.annotation.Cacheable;

import pl.robert.app.conference.domain.query.ConferenceQueryDto;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ConferenceFacade {

    ConferenceService service;

    @Cacheable("ConferenceSchedule")
    public ConferenceQueryDto find() {
        return service.find();
    }
}
