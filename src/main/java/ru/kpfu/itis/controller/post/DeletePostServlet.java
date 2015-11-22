package ru.kpfu.itis.controller.post;

import ru.kpfu.itis.repository.PostRepository;
import ru.kpfu.itis.util.IsAuthUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deletePost  ", urlPatterns = "/posts/delete")
public class DeletePostServlet extends HttpServlet {
    PostRepository postRepository = new PostRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (IsAuthUser.check(req)) {
            postRepository.deletePost(req.getParameter("id"));
        }
    }
}
