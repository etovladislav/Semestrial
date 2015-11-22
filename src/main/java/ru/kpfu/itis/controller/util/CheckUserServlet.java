package ru.kpfu.itis.controller.util;

import ru.kpfu.itis.repository.CheckUserRepository;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "checkUser", urlPatterns = "/checkuser")
public class CheckUserServlet extends HttpServlet {
    private CheckUserRepository checkUserRepository = new CheckUserRepository();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("login");
            boolean result = checkUserRepository.checkUserExists(username);
            if (result) {
                resp.setStatus(200);
                resp.getWriter().write("true"); //json format );

            } else {
                resp.setStatus(200);
                resp.getWriter().write("\"User already exists\""); //json format );
            }
    }
}
