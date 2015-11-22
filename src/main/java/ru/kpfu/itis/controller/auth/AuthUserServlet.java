package ru.kpfu.itis.controller.auth;

import ru.kpfu.itis.service.AuthUserService;
import ru.kpfu.itis.util.IsAuthUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(name = "authUser", urlPatterns = {"/auth"})
public class AuthUserServlet extends HttpServlet {
    private AuthUserService userService = new AuthUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isUser = userService.authUser(login, password);
        if (isUser) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("username", login);
            resp.sendRedirect("/users/" + login);
        } else {
            req.setAttribute("error", true);
            getServletContext().getRequestDispatcher("/auth.ftl").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    if (!IsAuthUser.check(req)) {
            getServletContext().getRequestDispatcher("/auth.ftl").forward(req, resp);
        } else {
            resp.sendRedirect("/users/" + req.getSession().getAttribute("username"));
        }
    }
}
