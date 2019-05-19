package pl.robert.app.shared;

import com.vaadin.ui.Notification;

public final class NotificationService {

    public static void showErrorNotification(String message) {
        Notification.show(message, Notification.Type.ERROR_MESSAGE).setDelayMsec(3000);
    }

    public static void showHumanizedNotification(String message) {
        Notification.show(message, Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);
    }
}
