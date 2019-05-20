package pl.robert.app.lecture.domain;

import pl.robert.app.user.domain.UserFacade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LectureConfiguration {

    @Bean
    LectureFacade lectureFacade(LectureRepository repository,
                                UserFacade userFacade) {

        LectureService service = new LectureService(repository, userFacade);

        return new LectureFacade(service,
                                 new LectureValidator(repository, service, userFacade));
    }
}
