package pl.robert.app.lecture.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
class LectureConfiguration {

    @Bean
    LectureFacade lectureFacade() {
        return new LectureFacade(new LectureService());
    }
}
