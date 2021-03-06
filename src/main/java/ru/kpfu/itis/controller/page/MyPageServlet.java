package ru.kpfu.itis.controller.page;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.util.IsAuthUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "im", urlPatterns = "/im")
public class MyPageServlet extends HttpServlet {
    private UserRepository userRepository = new UserRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getSession().getAttribute("username").toString();
        User user = userRepository.findUserByLogin(username);
        req.setAttribute("user", user);
        req.setAttribute("posts",userRepository.getCountPosts(user.getLogin()));
        if (IsAuthUser.check(req)) {
            req.setAttribute("auth", Boolean.TRUE);
        }
        getServletContext().getRequestDispatcher("/page.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
