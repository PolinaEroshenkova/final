package logic;

public class LoginLogic {
    private final static String ADMIN_LOGIN = "admin";
    private final static String ADMIN_PASSWORD = "admin";

    public static boolean checkLogin(String enterLogin, String enterPassword) {
        return ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASSWORD.equals(enterPassword);
    }
}
