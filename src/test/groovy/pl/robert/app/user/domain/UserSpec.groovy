package pl.robert.app.user.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.robert.app.shared.GlobalAuthorizationEntryPoint
import pl.robert.app.user.domain.dto.CreateUserDto
import pl.robert.app.user.domain.exception.InvalidUserException

import spock.lang.Specification
import spock.lang.Unroll

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

    @Unroll
    def 'Should throw an exception on null or blank fields = [#name | #email]'(String name, String email) {
        when:
        facade.create(new CreateUserDto(name, email))

        then:
        InvalidUserException exception = thrown()
        exception.message ==
                InvalidUserException.CAUSE.BLANK_NAME.message || InvalidUserException.CAUSE.BLANK_EMAIL.message

        where:
        name  |  email
        null  |  null
        null  | 'a@a.com'
        'Rob' |  null
        '   ' | '   '
        '   ' | 'a@a.com'
        'Rob' | '   '
    }

    def 'Should throw an exception cause length of name'() {
        when:
        facade.create(new CreateUserDto('moreThanFifteenCharacters', 'a@a.com'))

        then:
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.NAME_LENGTH.message
    }

    @Unroll
    def 'Should throw an exception cause name and email need to be unique = [#name | #email]'(String name, String email) {
        when: 'we create an user'
        facade.create(new CreateUserDto(name, email))

        then:
        InvalidUserException exception = thrown()
        exception.message ==
                InvalidUserException.CAUSE.NAME_EXISTS.message || InvalidUserException.CAUSE.EMAIL_EXISTS.message

        where:
        name   |  email
        'John' |  'john@email.com'
        'Mike' |  'john@email.com'
        'John' |  'mike@email.com'
    }

    @Unroll
    def 'Should throw an exception cause email format = #email'(String email) {
        when:
        facade.create(new CreateUserDto('Joe', email))

        then:
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.EMAIL_FORMAT.message

        where:
        email                          | _
        'plainaddress'                 | _
        '#@%^%#$@#$@#.com'             | _
        '@domain.com'                  | _
        'Joe Smith <email@domain.com>' | _
        'email.domain.com'             | _
        'email@domain@domain.com'      | _
        '.email@domain.com'            | _
        'email.@domain.com'            | _
        'email..email@domain.com'      | _
        'あいうえお@domain.com'          | _
        'email@domain.com (Joe Smith)' | _
        'email@domain'                 | _
    }

    def 'Should throw an exception cause name does not exists'() {
        when: 'we try to login into not exist account'
        facade.login('NotExistName')

        then:
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.NAME_NOT_EXISTS.message
    }

    def 'Should login and logout successfully'() {
        when: 'user is not authorized'
        !GlobalAuthorizationEntryPoint.isAuthorized()

        and: 'user log in'
        facade.login('Robert')

        and: 'user is authorized'
        GlobalAuthorizationEntryPoint.isAuthorized()

        and: 'user log out'
        facade.logout()

        then: 'user is not authorized'
        !GlobalAuthorizationEntryPoint.isAuthorized()
    }
}
