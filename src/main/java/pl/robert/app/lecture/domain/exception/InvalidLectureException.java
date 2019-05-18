package pl.robert.app.lecture.domain.exception;

import lombok.Getter;
import lombok.AllArgsConstructor;

import pl.robert.app.shared.NotificationService;

public class InvalidLectureException extends RuntimeException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        BLANK("Proszę podać identyfikator prelekcji"),
        FORMAT("Proszę podać wartość numeryczną"),
        FULL("Brak miejsc na wybraną prelekcję");

        String message;
    }

    public InvalidLectureException(CAUSE cause) {
        super(cause.message, null, false, false);

        NotificationService.showErrorNotification(cause.message);
    }
}
