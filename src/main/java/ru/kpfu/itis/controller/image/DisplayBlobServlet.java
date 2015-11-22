package ru.kpfu.itis.controller.image;

import org.apache.log4j.Logger;
import ru.kpfu.itis.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/avatar.jpg")
public class DisplayBlobServlet extends HttpServlet {
    static Logger log = Logger.getLogger(DisplayBlobServlet.class);

    private UserRepository userRepository = new UserRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("image/jpeg");
        FileInputStream in = new FileInputStream(new File(userRepository.getAvatar(request.getParameter("username"))));

        int length = 0;
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        out.flush();

    }
}