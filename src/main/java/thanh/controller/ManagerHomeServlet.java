package thanh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import thanh.Dao.CategoryDao;
import thanh.Dao.impl.CategoryDaoImpl;
import thanh.entity.User;

import java.io.IOException;

@WebServlet("/manager/home")
public class ManagerHomeServlet extends HttpServlet {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        req.setAttribute("categories", categoryDao.findByOwner(u.getId()));
        req.getRequestDispatcher("/manager/home.jsp").forward(req, resp);
    }
}
