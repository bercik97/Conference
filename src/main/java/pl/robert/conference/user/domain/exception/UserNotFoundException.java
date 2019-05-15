package pl.robert.conference.user.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("No user of id \"" + id + "\" found");
    }
}
