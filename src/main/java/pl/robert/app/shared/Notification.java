package pl.robert.app.shared;

interface Notification {

    void send(String email, String name, String msg, Long conferenceId);
}
