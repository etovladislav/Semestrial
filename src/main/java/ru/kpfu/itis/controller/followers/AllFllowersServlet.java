package ru.kpfu.itis.controller.followers;

import ru.kpfu.itis.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "allFollower", urlPatterns = "/all-follower/*")
public class AllFllowersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        String username = req.getPathInfo().split("/")[1];
        req.setAttribute("users", userRepository.getAllFollower(username));
        req.setAttribute("page","Followers");
        req.setAttribute("auth",req.getSession().getAttribute("username"));
        getServletContext().getRequestDispatcher("/followers.ftl").forward(req, resp);
    }
}
