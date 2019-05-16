package pl.robert.conference.shared;

import com.vaadin.ui.Notification;

public class GlobalAuthorizationEntryPoint {

    public static String name;

    public static boolean isAuthorized() {
        return name != null;
    }

    public static void login(String name) {
        GlobalAuthorizationEntryPoint.name = name;
        Notification.show("Zalogowałeś się!", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);
    }

    public static void logout() {
        GlobalAuthorizationEntryPoint.name = null;
        Notification.show("Wylogowałeś się!", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);
    }
}
