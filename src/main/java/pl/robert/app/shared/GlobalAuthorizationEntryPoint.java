package pl.robert.app.shared;

public final class GlobalAuthorizationEntryPoint {

    public static String name;

    public static boolean isAuthorized() {
        return name != null;
    }
}
