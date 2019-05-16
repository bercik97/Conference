package pl.robert.conference.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade facade(UserRepository repository) {
        return new UserFacade(new UserValidator(repository),
                              new UserService(repository));
    }
}
