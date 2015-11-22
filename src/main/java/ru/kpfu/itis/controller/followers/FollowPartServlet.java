package ru.kpfu.itis.controller.followers;

import ru.kpfu.itis.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "partFollow", urlPatterns = "/updateFollowers/*")
public class FollowPartServlet extends HttpServlet {
    private UserRepository userRepository = new UserRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getPathInfo().split("/")[1];
        Boolean isFollow = userRepository.isFollow(req.getSession().getAttribute("username").toString(), username);
        req.setAttribute("isFollow", isFollow);
        req.setAttribute("isMyPage", !req.getSession().getAttribute("username").toString().equals(username));
        getServletContext().getRequestDispatcher("/follow.ftl").forward(req, resp);
    }
}
