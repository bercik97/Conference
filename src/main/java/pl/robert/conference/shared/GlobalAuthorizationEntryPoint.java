package pl.robert.conference.shared;

public class GlobalAuthorizationEntryPoint {

    public static String name;

    public static boolean isAuthorized() {
        return name != null;
    }
}
