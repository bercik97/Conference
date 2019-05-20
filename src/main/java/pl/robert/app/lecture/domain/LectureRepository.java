package pl.robert.app.lecture.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;

interface LectureRepository extends JpaRepository<Lecture, Long> {

    Lecture findLectureById(long id);

    @Query(value = "SELECT lecture_id FROM users_lectures WHERE user_id = :userId", nativeQuery = true)
    List<Long> findAlreadySubscribedLecturesByUsername(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users_lectures WHERE user_id = :userId AND lecture_id = :lectureId", nativeQuery = true)
    void deleteLectureByUserId(@Param("userId") Long userId, @Param("lectureId") Long lectureId);
}
