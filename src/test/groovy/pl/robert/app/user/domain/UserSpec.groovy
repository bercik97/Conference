package pl.robert.app.user.domain

import spock.lang.Shared
import spock.lang.Specification

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import pl.robert.app.user.domain.dto.CreateUserDto

import java.util.concurrent.ConcurrentHashMap

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserSpec extends Specification {

    @Shared
    UserFacade userFacade

    @Shared
    ConcurrentHashMap<String, User> db = new ConcurrentHashMap<>()

    def setupSpec() {
        userFacade = new UserConfiguration().userFacade(db)
    }

    def 'Should add user'() {
        when: 'we add an user'
        userFacade.create(new CreateUserDto('John', 'john@email.com'))

        then: 'system has this user'
        db.size() == 1
    }
}
