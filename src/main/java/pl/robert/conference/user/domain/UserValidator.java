package pl.robert.conference.user.domain;

import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.apache.logging.log4j.util.Strings;

import pl.robert.conference.user.domain.dto.CreateUserDto;
import pl.robert.conference.user.domain.exception.InvalidUserException;

import java.util.regex.Pattern;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserValidator {

    final static int COL_MAX_LENGTH_NAME = 15;
    private Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    UserRepository repository;

    void checkInputDto(CreateUserDto dto) {
        InvalidUserException.CAUSE cause = null;

        if (Strings.isBlank(dto.getName())) {
            cause = InvalidUserException.CAUSE.BLANK_NAME;
        } else if (dto.getName().length() < 2 || dto.getName().length() > COL_MAX_LENGTH_NAME) {
            cause = InvalidUserException.CAUSE.LENGTH;
        } else if (repository.findUserByName(dto.getName()) != null) {
            cause = InvalidUserException.CAUSE.EXISTS;
        }

        if (cause != null) {
            throw new InvalidUserException(cause);
        }

        validateEmail(dto.getEmail());
    }

    void checkInputName(String name) throws InvalidUserException {
        InvalidUserException.CAUSE cause = null;

        if (Strings.isBlank(name)) {
            cause = InvalidUserException.CAUSE.BLANK_NAME;
        } else if (repository.findUserByName(name) == null) {
            cause = InvalidUserException.CAUSE.NOT_EXISTS;
        }

        if (cause != null) {
            throw new InvalidUserException(cause);
        }
    }

    void checkInputEmail(String email) throws InvalidUserException {
        validateEmail(email);
    }

    private void validateEmail(String email) {
        InvalidUserException.CAUSE cause = null;

        if (Strings.isBlank(email)) {
            cause = InvalidUserException.CAUSE.BLANK_EMAIL;
        } else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
            cause = InvalidUserException.CAUSE.EMAIL;
        }

        if (cause != null) {
            throw new InvalidUserException(cause);
        }
    }
}
