package pl.robert.conference.shared;

import com.vaadin.ui.Notification;

public class NotificationService {

    public static void showErrorNotification(String message) {
        Notification.show(message, Notification.Type.ERROR_MESSAGE).setDelayMsec(3000);
    }

    public static void showHumanizedNotification(String message) {
        Notification.show(message, Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);
    }
}
