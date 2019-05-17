package pl.robert.app.conference.domain.exception;

public class ConferenceNotFoundException extends RuntimeException {

    public ConferenceNotFoundException() {
        super("No conference found", null, false, false);
    }
}
