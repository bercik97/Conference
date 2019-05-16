package pl.robert.app.user.domain.exception;

import lombok.Getter;
import lombok.AllArgsConstructor;

import pl.robert.app.shared.NotificationService;

public class InvalidUserException extends RuntimeException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        BLANK_NAME("Imię użytkownika jest wymagane"),
        BLANK_EMAIL("Email użytkownika jest wymagany"),
        LENGTH("Imię powinno posiadać od 2 do 15 znaków"),
        EXISTS("Podane imię jest już zajęte"),
        NOT_EXISTS("Podane imię użytkownika nie istnieje"),
        EMAIL("Proszę wprowadzić poprawny adres email");

        String message;
    }

    public InvalidUserException(CAUSE cause) {
        super(cause.message, null, false, false);

        NotificationService.showErrorNotification(cause.message);
    }
}
