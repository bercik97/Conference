package pl.robert.app.conference.domain;

import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class InMemoryConferenceRepository implements ConferenceRepository {

    ConcurrentHashMap<Long, Conference> map;

    public InMemoryConferenceRepository(ConcurrentHashMap<Long, Conference> map) {
        this.map = map;
        prepareMap();
    }

    private void prepareMap() {
        map.put(
                1L,
                new Conference(1L, "Conference", "Fake conference", new HashSet<>(), new HashSet<>())
        );
    }

    @Override
    public Set<Conference> findAll() {
        return new HashSet<>(map.values());
    }
}
