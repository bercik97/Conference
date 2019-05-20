package pl.robert.app.lecture.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import pl.robert.app.user.domain.UserFacade

import spock.lang.Specification

@SpringBootTest
class LectureSpec extends Specification {

    @Autowired
    LectureFacade facade

    @Autowired
    UserFacade userFacade

    def setup() {
        userFacade.login('Robert')
    }

    def 'Should subscribe lecture'() {
        when:
        facade.subscribeLecture('1')

        then:
        facade.findAlreadySubscribedLectures().size() == 1
    }

    def 'Should unsubscribe lecture'() {
        when:
        facade.unsubscribeLecture('1')

        then:
        facade.findAlreadySubscribedLectures().size() == 0
    }
}
