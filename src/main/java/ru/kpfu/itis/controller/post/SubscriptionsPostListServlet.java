package ru.kpfu.itis.controller.post;

import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.repository.PostRepository;
import ru.kpfu.itis.util.IsAuthUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "news", urlPatterns = "/news")
public class SubscriptionsPostListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostRepository postRepository = new PostRepository();
        List<Post> postList = postRepository.getNewsPosts(req.getSession().getAttribute("username").toString());
        req.setAttribute("posts", postList);
        if (IsAuthUser.check(req)) {
            req.setAttribute("auth", Boolean.TRUE);
        }else{
            resp.sendRedirect("/login");
        }
        getServletContext().getRequestDispatcher("/subscrpost.ftl").forward(req, resp);
    }
}
