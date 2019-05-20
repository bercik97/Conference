package pl.robert.app.conference.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ConferenceConfiguration {

    @Bean
    ConferenceFacade conferenceFacade(ConferenceRepository repository) {
        return new ConferenceFacade(new ConferenceService(repository));
    }
}
