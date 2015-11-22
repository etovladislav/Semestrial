package ru.kpfu.itis.util;

import javax.servlet.http.HttpServletRequest;

public class IsAuthUser {

    public static boolean check(HttpServletRequest req) {
        if (req.getSession().getAttribute("username") != null) {
            return true;
        }
        return false;
    }
}
