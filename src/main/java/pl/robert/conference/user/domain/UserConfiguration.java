package pl.robert.conference.user.domain;

import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    UserFacade facade(UserRepository repository) {
        return new UserFacade(repository);
    }
}
