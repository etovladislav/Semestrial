package ru.kpfu.itis.controller.image;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.util.Avatar;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "savePhoto", urlPatterns = "/saveImg")
public class SaveBlobServlet extends HttpServlet {
    private UserRepository userRepository = new UserRepository();
    private final static String UPLOAD_DIRECTORY = "/home/vladislav/Рабочий стол/Учеба/Subjects/" +
            "Programming/Semestrial/Semestrial/" +
            "TimeScaleBE/src/main/webapp/avatarUser";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(req);
                String path = null;
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        path = UPLOAD_DIRECTORY + File.separator + req.getSession().getAttribute("username") + name;
                        item.write(new File(path));
                    }
                }
                String oldAvatar = userRepository.updateAvatar(req.getSession().getAttribute("username").toString(), path);
                if(!oldAvatar.equals(Avatar.DEFAULT)){
                    File file1 = new File(oldAvatar);
                    file1.deleteOnExit();
                }
                resp.sendRedirect("/im");
            } catch (Exception ex) {

            }
        } else {
            req.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
