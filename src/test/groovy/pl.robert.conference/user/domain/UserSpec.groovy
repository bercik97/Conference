package pl.robert.conference.user.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

import pl.robert.conference.user.domain.dto.CreateUserDto
import pl.robert.conference.user.domain.exception.UserNotFoundException

import spock.lang.Shared
import spock.lang.Specification

class UserSpec extends Specification {

    @Shared
    UserFacade facade

    def setupSpec() {
        facade = new UserConfiguration().facade()
    }

    def 'Should create user'() {
        when: 'we create an user'
        facade.create(new CreateUserDto(1L, 'James', 'james.gosling@coffee.com'))

        then: 'system has this user'
        facade.read(1L).name == 'James'
    }

    def 'Should update user email'() {
        when: 'we update an user'
        facade.update(1L, 'James', 'gosling.james@coffee.pl')

        then: 'system has updated users email'
        facade.read(1L).email == 'gosling.james@coffee.pl'
    }

    def 'Should delete user'() {
        when: 'we delete an user'
        facade.delete(1L)

        and: 'check if system has this user'
        facade.read(1L)

        then: 'we throw an exception'
        thrown UserNotFoundException
    }

    def 'Should list users'() {
        given: 'we add two users to system'
        facade.create(new CreateUserDto(1L, 'Rob', 'rob.mee@spring.io'))
        facade.create(new CreateUserDto(2L, 'Linus', 'linus.torvalds@penguin.com'))

        when: 'we ask for all users'
        Page<User> foundUsers = facade.readAll(new PageRequest(0, 5))

        then: 'system has this users'
        foundUsers.stream().count() == 2
    }
}
