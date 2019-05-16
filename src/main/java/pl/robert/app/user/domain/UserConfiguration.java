package pl.robert.app.user.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade facade(UserRepository repository) {
        return new UserFacade(new UserValidator(repository),
                              new UserService(repository),
                              new UserAuthorizationService());
    }
}
