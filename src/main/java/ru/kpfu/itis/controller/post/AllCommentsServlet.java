package ru.kpfu.itis.controller.post;

import ru.kpfu.itis.repository.PostRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "allcom", urlPatterns = "/comment/all")
public class AllCommentsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostRepository postRepository = new PostRepository();
        req.setAttribute("comments", postRepository.getComments(Long.valueOf(req.getParameter("id"))));
        getServletContext().getRequestDispatcher("/comments-list.ftl").forward(req, resp);
    }

}
