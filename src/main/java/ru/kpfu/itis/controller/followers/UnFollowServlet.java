package ru.kpfu.itis.controller.followers;

import ru.kpfu.itis.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "unFollow", urlPatterns = "/unFollow")
public class UnFollowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        String username = req.getSession().getAttribute("username").toString();
        String usernameFollow = req.getParameter("usernameFollow");
        userRepository.unFollow(username,usernameFollow);
    }
}
