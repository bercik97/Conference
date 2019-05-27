package pl.robert.app.conference.domain

import spock.lang.Shared
import spock.lang.Specification

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import java.util.concurrent.ConcurrentHashMap

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ConferenceSpec extends Specification {

    @Shared
    ConferenceFacade facade

    @Shared
    ConcurrentHashMap<Long, Conference> db = new ConcurrentHashMap<>()

    def setupSpec() {
        facade = new ConferenceConfiguration().conferenceFacade(db)
    }

    def 'Should find any conference'() {
        expect:
        facade.find() != null
    }
}
