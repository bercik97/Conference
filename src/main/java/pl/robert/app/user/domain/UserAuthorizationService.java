package pl.robert.app.user.domain;

import com.vaadin.server.Page;

import pl.robert.app.shared.GlobalAuthorizationEntryPoint;

class UserAuthorizationService {

    void login(String name) {
        GlobalAuthorizationEntryPoint.name = name;
        Page.getCurrent().reload();
    }

    void logout() {
        GlobalAuthorizationEntryPoint.name = null;
        Page.getCurrent().reload();
    }
}
