package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "user";

    /* ���� �α����� ������� ID�� ���� */
    public static String getUserFromSession(HttpSession session) {
        String userId = (String)session.getAttribute(USER_SESSION_KEY);
        return userId;
    }

    /* �α����� ���������� �˻� */
    public static boolean isLogined(HttpSession session) {
        if (getUserFromSession(session) != null) {
            return true;
        }
        return false;
    }
 
    /* ���� �α����� ������� ID�� userId���� �˻� */
    public static boolean isLoginUser(String userId, HttpSession session) {
        if (!isLogined(session)) {
            return false;
        }
        if (userId == null) {
            return false;
        }
        return userId.equals(getUserFromSession(session));
    }    
}
