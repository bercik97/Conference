package pl.robert.app.conference.domain

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import spock.lang.Specification

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SpringBootTest
class ConferenceSpec extends Specification {

    @Autowired
    ConferenceFacade facade

    def 'Should find any conference'()  {
        expect:
        facade.find() != null
    }
}
