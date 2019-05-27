package pl.robert.app.lecture.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LectureConfiguration {

    @Bean
    LectureFacade lectureFacade(LectureRepository repository) {

        LectureService service = new LectureService(repository);

        return new LectureFacade(service,
                                 new LectureValidator(repository, service));
    }
}
