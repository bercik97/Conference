package pl.robert.app.lecture.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

interface LectureRepository extends Repository<Lecture, Long> {

    Lecture findLectureById(Long id);

    @Query(value = "SELECT lecture_id FROM users_lecture WHERE user_id = :userId", nativeQuery = true)
    List<Long> findAlreadySubscribedLecturesByUserId(@Param("userId") Long userId);
}
