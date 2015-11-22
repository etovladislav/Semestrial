package ru.kpfu.itis.controller.users;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "searchUser", urlPatterns = "/search/users")
public class SearchUsersServlet extends HttpServlet{
    private UserRepository userRepository = new UserRepository();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userRepository.searchUsers(req.getParameter("username"));
        req.setAttribute("users",users);
        getServletContext().getRequestDispatcher("/users.ftl").forward(req, resp);
    }


}
