package pl.robert.conference.user.domain;

import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    UserFacade facade() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        return new UserFacade(repository);
    }
}
