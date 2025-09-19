package thanh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import thanh.Dao.UserDao;
import thanh.Dao.impl.UserDaoImpl;
import thanh.entity.User;

import java.io.IOException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User u = userDao.findByUsername(username);

        if (u != null && u.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", u);
            if (u.getRoleId() == 1)
                resp.sendRedirect(req.getContextPath() + "/user/home");
            else if (u.getRoleId() == 2)
                resp.sendRedirect(req.getContextPath() + "/manager/home");
            else
                resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
