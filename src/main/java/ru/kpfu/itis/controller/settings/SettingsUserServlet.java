package ru.kpfu.itis.controller.settings;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.SettingsUserRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.RegistrationUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "settings", urlPatterns = "/settings")
public class SettingsUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.findUserByLogin(req.getSession().getAttribute("username").toString());
        req.setAttribute("user",user);
        req.setAttribute("auth",Boolean.TRUE);
        getServletContext().getRequestDispatcher("/settings.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        SettingsUserRepository settingsUserRepository = new SettingsUserRepository();
        String firstname = req.getParameter("firstName");
        String lastname = req.getParameter("lastName");
        String username = req.getSession().getAttribute("username").toString();
        settingsUserRepository.updateInfoUser(firstname, lastname, username);
        resp.sendRedirect("/users/"+req.getSession().getAttribute("username"));
    }
}
