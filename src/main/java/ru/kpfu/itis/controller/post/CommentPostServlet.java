package ru.kpfu.itis.controller.post;

import ru.kpfu.itis.repository.PostRepository;
import ru.kpfu.itis.util.IsAuthUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "comment", urlPatterns = "/comment")
public class CommentPostServlet extends HttpServlet {
    private PostRepository postRepository = new PostRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postRepository.addComment(Long.valueOf(req.getParameter("postId")),
                req.getSession().getAttribute("username").toString(), req.getParameter("text"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long postId = Long.valueOf(req.getParameter("id_post"));
        req.setAttribute("comments", postRepository.getComments(postId));
        req.setAttribute("post", postRepository.getPost(postId));
        if (IsAuthUser.check(req)) {
            req.setAttribute("auth", Boolean.TRUE);
        }
        getServletContext().getRequestDispatcher("/comments.ftl").forward(req, resp);
    }
}
