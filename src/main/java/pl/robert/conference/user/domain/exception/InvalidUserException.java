package pl.robert.conference.user.domain.exception;

import lombok.Getter;
import lombok.AllArgsConstructor;
import com.vaadin.ui.Notification;

public class InvalidUserException extends RuntimeException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        BLANK("Proszę wypełnić puste pole(a)"),
        LENGTH("Imię powinno posiadać od 2 do 15 znaków"),
        EXISTS("Podany login jest już zajęty"),
        EMAIL("Proszę wprowadzić poprawny adres email");

        String message;
    }

    public InvalidUserException(CAUSE cause) {
        super(cause.message, null, false, false);

        Notification.show(cause.message, Notification.Type.ERROR_MESSAGE);
    }
}
