package pl.robert.app.lecture.domain.exception;

import lombok.Getter;
import lombok.AllArgsConstructor;

import pl.robert.app.shared.ParameterizedException;

public class InvalidLectureException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        BLANK("Proszę podać identyfikator prelekcji"),
        FORMAT("Proszę podać wartość numeryczną"),
        NOT_EXISTS("Podany identyfikator prelekcji nie istnieje"),
        FULL("Brak miejsc na wybraną prelekcję"),
        SUBSCRIBED("Jesteś już zapisany na inną prelekcję o tej samej godzinie tego samego dnia"),
        UNSUBSCRIBED("Nie możesz wypisać się z prelekcji na którą nie jesteś zapisany");

        String message;
    }

    public InvalidLectureException(CAUSE cause) {
        super(cause.message);
    }
}
