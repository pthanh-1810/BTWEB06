package thanh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import thanh.Dao.CategoryDao;
import thanh.Dao.impl.CategoryDaoImpl;

import java.io.IOException;

@WebServlet("/user/home")
public class UserHomeServlet extends HttpServlet {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("categories", categoryDao.findAll());
        req.getRequestDispatcher("/user/home.jsp").forward(req, resp);
    }
}
