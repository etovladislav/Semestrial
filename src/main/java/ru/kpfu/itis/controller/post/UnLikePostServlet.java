package ru.kpfu.itis.controller.post;

import ru.kpfu.itis.repository.PostRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "unLike", urlPatterns = {"/unLikePost"})
public class UnLikePostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostRepository postRepository = new PostRepository();
        postRepository.unLikePost(Long.valueOf(req.getParameter("idPost")),
                req.getSession().getAttribute("username").toString());
    }
}
