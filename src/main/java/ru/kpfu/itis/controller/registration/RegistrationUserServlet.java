package ru.kpfu.itis.controller.registration;

import ru.kpfu.itis.service.RegistrationUserService;
import ru.kpfu.itis.util.IsAuthUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegistrationUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (IsAuthUser.check(req)) {
            req.setAttribute("auth", Boolean.TRUE);
            resp.sendRedirect("/im");
        } else {
            getServletContext().getRequestDispatcher("/registration.ftl").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationUserService registrationUserService = new RegistrationUserService();
        registrationUserService.registrationUser(req.getParameterMap());
        resp.sendRedirect("/auth");
    }
}
