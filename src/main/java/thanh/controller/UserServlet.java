package thanh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import thanh.Dao.UserDao;
import thanh.Dao.impl.UserDaoImpl;
import thanh.entity.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user")
public class UserServlet extends HttpServlet {
    private UserDao dao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String q = req.getParameter("q");
        List<User> list = (q != null && !q.isEmpty()) ? dao.search(q) : dao.findAll();
        req.setAttribute("users", list);
        req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            User u = new User();
            u.setUsername(req.getParameter("username"));
            u.setPassword(req.getParameter("password"));
            u.setRoleId(Integer.parseInt(req.getParameter("roleId")));
            dao.insert(u);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            User u = dao.findById(id);
            if (u != null) {
                u.setUsername(req.getParameter("username"));
                u.setPassword(req.getParameter("password"));
                u.setRoleId(Integer.parseInt(req.getParameter("roleId")));
                dao.update(u);
            }
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/user");
    }
}
