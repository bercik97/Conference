package pl.robert.app.conference.domain.exception;

import pl.robert.app.shared.ParameterizedException;

public class ConferenceNotFoundException extends ParameterizedException {

    public ConferenceNotFoundException() {
        super("No conference found");
    }
}
