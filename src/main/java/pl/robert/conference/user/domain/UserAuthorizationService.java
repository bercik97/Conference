package pl.robert.conference.user.domain;

import com.vaadin.server.Page;

import pl.robert.conference.shared.GlobalAuthorizationEntryPoint;
import pl.robert.conference.shared.NotificationService;

class UserAuthorizationService {

    void login(String name) {
        GlobalAuthorizationEntryPoint.name = name;
        NotificationService.showHumanizedNotification("Zalogowałeś się!");
    }

    void logout() {
        GlobalAuthorizationEntryPoint.name = null;
        Page.getCurrent().reload();
    }
}
