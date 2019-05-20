package pl.robert.app.lecture.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import pl.robert.app.lecture.domain.exception.InvalidLectureException
import pl.robert.app.user.domain.UserFacade

import spock.lang.Specification
import spock.lang.Unroll

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

    @Unroll
    def 'Should throw an exception cause lecture id is null or blank = [#id]'(String id) {
        when:
        facade.subscribeLecture(id)

        then:
        InvalidLectureException exception = thrown()
        exception.message == InvalidLectureException.CAUSE.BLANK.message

        where:
        id   |_
        null |_
        '  ' |_
    }

    @Unroll
    def 'Should throw an exception cause lecture id is wrong format = [#id]'(String id) {
        when:
        facade.subscribeLecture(id)

        then:
        InvalidLectureException exception = thrown()
        exception.message == InvalidLectureException.CAUSE.FORMAT.message

        where:
        id    |_
        'num' |_
        'n m' |_
        '0.5' |_
        '0,5' |_
        '@#%' |_
        '  5' |_
        '5  ' |_
        '.5 ' |_
        '5. ' |_
    }

    @Unroll
    def 'Should throw an exception cause the number of lecture doest not exists = [#id]'(String id) {
        when:
        facade.subscribeLecture(id)

        then:
        InvalidLectureException exception = thrown()
        exception.message == InvalidLectureException.CAUSE.NOT_EXISTS.message

        where:
        id      |_
        '1000'  |_
        '-1000' |_
    }

    def 'Should throw an exception cause the number of left seats is 0'() {
        when:
        facade.subscribeLecture('2')

        then:
        InvalidLectureException exception = thrown()
        exception.message == InvalidLectureException.CAUSE.FULL.message
    }

    def 'Should throw an exception cause user already subscribed current lecture'() {
        when:
        facade.subscribeLecture('1')

        and:
        facade.subscribeLecture('1')

        then:
        InvalidLectureException exception = thrown()
        exception.message == InvalidLectureException.CAUSE.SUBSCRIBED.message

        and: 'we cleanup subscribed lecture'
        facade.unsubscribeLecture('1')
    }

    def 'Should throw and exception cause user try to unsubscribed lecture which he didnt subscribed before'() {
        when:
        facade.unsubscribeLecture('3')

        then:
        InvalidLectureException exception = thrown()
        exception.message == InvalidLectureException.CAUSE.UNSUBSCRIBED.message
    }

    def 'Should find already subscribed lectures'() {
        when: 'actual size of subscribed lectures is 0'
        facade.findAlreadySubscribedLectures().size() == 0

        and:
        facade.subscribeLecture('1')

        and:
        facade.subscribeLecture('4')

        and:
        facade.subscribeLecture('7')

        and:
        facade.subscribeLecture('10')

        then: 'actual size is 4'
        facade.findAlreadySubscribedLectures().size() == 4
    }

    def 'Should find ids of already subscribed lectures'() {
        expect:
        facade.findIdsOfAlreadySubscribedLectures() == '1, 4, 7, 10, '
    }
}
