package pl.robert.app.conference.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import spock.lang.Specification

@SpringBootTest
class ConferenceSpec extends Specification {

    @Autowired
    ConferenceFacade facade

    def 'Should find any conference'()  {
        expect:
        facade.find() != null
    }
}
