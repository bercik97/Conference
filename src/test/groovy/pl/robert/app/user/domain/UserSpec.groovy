package pl.robert.app.user.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.robert.app.user.domain.dto.CreateUserDto
import spock.lang.Specification

@SpringBootTest
class UserSpec extends Specification {

    @Autowired
    private UserFacade facade

    @Autowired
    private UserRepository repository

    def 'Should add user'() {
        when: 'we add an user'
        facade.create(new CreateUserDto('John', 'john@email.com'))

        then: 'system has this user'
        repository.findUserByName('John').isPresent()
    }

    def 'Should update user email'() {
        given: 'initialized dto'
        def dto = new CreateUserDto('Cody', 'cody@email.com')

        when: 'we add an user'
        facade.create(dto)

        and: 'we assign old email to variable'
        def oldEmail = dto.email

        and: 'we update an email'
        facade.update(dto.name, 'newcody@email.com')

        then: 'system hasnt got this old email'
        !repository.findUserByEmail(oldEmail).isPresent()
    }
}
