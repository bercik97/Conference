package pl.robert.app.conference.domain;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ConferenceConfiguration {

    @Bean
    ConferenceFacade conferenceFacade(ConferenceRepository repository) {
        return new ConferenceFacade(new ConferenceService(repository));
    }

    ConferenceFacade conferenceFacade(ConcurrentHashMap<Long, Conference> db) {

        InMemoryConferenceRepository repository = new InMemoryConferenceRepository(db);

        return new ConferenceFacade(new ConferenceService(repository));
    }
}
