package pl.robert.app.user.domain.exception;

import lombok.Getter;
import lombok.AllArgsConstructor;

import pl.robert.app.shared.ParameterizedException;

public class InvalidUserException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        BLANK_NAME("Imię użytkownika jest wymagane"),
        BLANK_EMAIL("Email użytkownika jest wymagany"),
        NAME_LENGTH("Imię powinno posiadać maksymalnie 15 znaków"),
        NAME_EXISTS("Podane imię jest już zajęte"),
        EMAIL_EXISTS("Podany adres email jest już zajęty"),
        NAME_NOT_EXISTS("Podane imię użytkownika nie istnieje"),
        EMAIL_FORMAT("Proszę wprowadzić poprawny adres email");

        String message;
    }

    public InvalidUserException(CAUSE cause) {
        super(cause.message);
    }
}
