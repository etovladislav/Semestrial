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

@WebServlet(name = "allUsers", urlPatterns = "/all-users")
public class AllUsersServlet extends HttpServlet{
    private UserRepository userRepository = new UserRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userRepository.getAllUsers();
        req.setAttribute("users",userList);
        req.setAttribute("auth",req.getSession().getAttribute("username"));
        getServletContext().getRequestDispatcher("/all-users.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
