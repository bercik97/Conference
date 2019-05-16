package pl.robert.app.shared;

public class GlobalAuthorizationEntryPoint {

    public static String name;

    public static boolean isAuthorized() {
        return name != null;
    }
}
