package pl.robert.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.robert.app.conference.domain.ConferenceFacade;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository repository,
                          ConferenceFacade facade) {
        return new UserFacade(new UserValidator(repository),
                              new UserService(repository, new UserFactory(facade)),
                              new UserAuthorizationService());
    }

    UserFacade userFacade(ConcurrentHashMap<String, User> db) {

        InMemoryUserRepository repository = new InMemoryUserRepository(db);

        return new UserFacade(new UserValidator(repository),
                              new UserService(repository, new UserFactory(null)),
                              new UserAuthorizationService());
    }
}
