package ru.kpfu.itis.controller.post;

import ru.kpfu.itis.repository.CheckUserRepository;
import ru.kpfu.itis.repository.PostRepository;
import ru.kpfu.itis.util.IsAuthUser;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "addPost", urlPatterns = "/posts/add/*")
public class AddPostUserServlet extends HttpServlet {
    PostRepository postRepository = new PostRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (IsAuthUser.check(req)) {
            postRepository.addPost(req.getPathInfo().split("/")[1], req.getParameter("text"), req.getSession().getAttribute("username").toString());
            req.setAttribute("auth", req.getSession().getAttribute("username"));
            getServletContext().getRequestDispatcher("/postsList.ftl").forward(req, resp);
        }
    }
}