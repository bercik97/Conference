package pl.robert.app.conference.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
class ConferenceConfiguration {

    @Bean
    ConferenceFacade conferenceFacade(ConferenceRepository repository) {
        return new ConferenceFacade(new ConferenceService(repository));
    }
}
