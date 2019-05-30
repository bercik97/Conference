package pl.robert.app.lecture.domain;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import pl.robert.app.user.domain.query.UserQueryDto;

import static pl.robert.app.lecture.domain.LectureType.KNOWLEDGE;
import static pl.robert.app.lecture.domain.LectureType.TECHNOLOGY;
import static pl.robert.app.lecture.domain.LectureType.INSPIRATION;

@FieldDefaults(level = AccessLevel.PRIVATE)
class InMemoryLectureRepository implements LectureRepository {

    ConcurrentHashMap<Long, Lecture> map;

    public InMemoryLectureRepository(ConcurrentHashMap<Long, Lecture> map) {
        this.map = map;
        prepareMap();
    }

    private void prepareMap() {
        map.put(1L, new Lecture(1L, INSPIRATION, "1", "10:00-11-45", 5));
        map.put(2L, new Lecture(2L, TECHNOLOGY, "1", "10:00-11-45", 0));
        map.put(3L, new Lecture(3L, KNOWLEDGE, "1", "10:00-11-45", 5));
        map.put(4L, new Lecture(4L, INSPIRATION, "1", "12:00-13:45", 5));
        map.put(7L, new Lecture(7L, INSPIRATION, "2", "10:00-11-45", 5));
        map.put(10L, new Lecture(10L, INSPIRATION, "2", "12:00-13-45", 5));
    }

    @Override
    public Lecture findLectureById(Long id) {
        return map.get(id);
    }

    @Override
    public List<Long> findAlreadySubscribedLecturesByUsername(Long userId) {

        List<Long> ids = new LinkedList<>();

        for (Map.Entry<Long, Lecture> entr : map.entrySet()) {
            if (!entr.getValue().getUsers().isEmpty()) {
                for (UserQueryDto user : entr.getValue().getUsers()) {
                    if (userId.equals(user.getId())) {
                        ids.add(entr.getKey());
                    }
                }
            }
        }

        return ids;
    }
}
