package pl.robert.app.user.domain;

import pl.robert.app.shared.GlobalAuthorizationEntryPoint;

class UserAuthorizationService {

    void login(String name) {
        GlobalAuthorizationEntryPoint.name = name;
    }

    void logout() {
        GlobalAuthorizationEntryPoint.name = null;
    }
}
