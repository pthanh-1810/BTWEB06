package thanh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import thanh.Dao.CategoryDao;
import thanh.Dao.impl.CategoryDaoImpl;
import thanh.entity.Category;
import thanh.entity.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/category")
public class CategoryServlet extends HttpServlet {
    private CategoryDao dao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        if ("edit".equals(action) && id != null) {
            Category c = dao.findById(Integer.parseInt(id));
            req.setAttribute("category", c);
        }
        String q = req.getParameter("q");
        List<Category> list = (q != null && !q.isEmpty()) ? dao.search(q) : dao.findAll();
        req.setAttribute("categories", list);
        req.getRequestDispatcher("/admin/categories.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if ("create".equals(action)) {
            Category c = new Category();
            c.setName(req.getParameter("name"));
            c.setDescription(req.getParameter("description"));
            c.setOwner(user);
            dao.insert(c);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category c = dao.findById(id);
            if (c != null) {
                c.setName(req.getParameter("name"));
                c.setDescription(req.getParameter("description"));
                dao.update(c);
            }
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/category");
    }
}
