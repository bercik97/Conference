package pl.robert.app.lecture.domain;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class InMemoryLectureRepository implements LectureRepository {

    ConcurrentHashMap<Long, Lecture> map;

    @Override
    public Lecture findLectureById(Long id) {
        return map.get(id);
    }

    @Override
    public List<Long> findAlreadySubscribedLecturesByUsername(Long userId) {
        return null;
    }
}
