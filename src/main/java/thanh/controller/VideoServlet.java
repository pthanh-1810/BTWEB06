package thanh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import thanh.Dao.CategoryDao;
import thanh.Dao.VideoDao;
import thanh.Dao.impl.CategoryDaoImpl;
import thanh.Dao.impl.VideoDaoImpl;
import thanh.entity.Category;
import thanh.entity.User;
import thanh.entity.Video;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/video")
public class VideoServlet extends HttpServlet {
    private VideoDao videoDao = new VideoDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String q = req.getParameter("q");
        List<Video> list = (q != null && !q.isEmpty()) ? videoDao.search(q) : videoDao.findAll();
        req.setAttribute("videos", list);
        req.setAttribute("categories", categoryDao.findAll());
        req.getRequestDispatcher("/admin/videos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if ("create".equals(action)) {
            Video v = new Video();
            v.setTitle(req.getParameter("title"));
            v.setUrl(req.getParameter("url"));
            Category c = categoryDao.findById(Integer.parseInt(req.getParameter("categoryId")));
            v.setCategory(c);
            v.setUploader(user);
            videoDao.insert(v);

        } else if ("update".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                Video v = videoDao.findById(id);
                if (v != null) {
                    v.setTitle(req.getParameter("title"));
                    v.setUrl(req.getParameter("url"));
                    Category c = categoryDao.findById(Integer.parseInt(req.getParameter("categoryId")));
                    v.setCategory(c);
                    videoDao.update(v);
                }
            }

        } else if ("delete".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                videoDao.delete(id);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/admin/video");
    }

}
