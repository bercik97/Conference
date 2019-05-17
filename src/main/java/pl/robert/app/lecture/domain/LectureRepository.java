package pl.robert.app.lecture.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface LectureRepository extends JpaRepository<Lecture, Long> {

    Lecture findLectureById(long id);
}
