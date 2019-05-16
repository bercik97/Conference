package pl.robert.app.user.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

import pl.robert.app.user.domain.dto.CreateUserDto
import pl.robert.app.user.domain.dto.UserDto
import pl.robert.app.user.domain.exception.UserNotFoundException

import spock.lang.Shared
import spock.lang.Specification

class UserSpec extends Specification {

    @Shared
    InMemoryUserRepository db

    def setupSpec() {
        db = new InMemoryUserRepository()
    }

    def 'Should create user'() {
        when: 'we create an user'
        db.create(1L, new CreateUserDto('James', 'james.gosling@coffee.com'))

        then: 'system has this user'
        db.read(1L).name == 'James'
    }

    def 'Should update user email'() {
        when: 'we update an user'
        db.update(1L, 'James', 'gosling.james@coffee.pl')

        then: 'system has updated users email'
        db.read(1L).email == 'gosling.james@coffee.pl'
    }

    def 'Should delete user'() {
        when: 'we delete an user'
        db.delete(1L)

        and: 'check if system has this user'
        db.read(1L)

        then: 'we throw an exception'
        thrown UserNotFoundException
    }

    def 'Should list users'() {
        given: 'we add two users to system'
        db.create(1L, new CreateUserDto('Rob', 'rob.mee@spring.io'))
        db.create(2L, new CreateUserDto('Linus', 'linus.torvalds@penguin.com'))

        when: 'we ask for all users'
        Page<UserDto> foundUsers = db.readAll(new PageRequest(0, 5))

        then: 'system has this users'
        foundUsers.stream().count() == 2
    }
}
