package thanh.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import thanh.entity.User;

import java.io.IOException;

@WebFilter(urlPatterns = { "/user/*", "/manager/*", "/admin/*", "/user/home", "/manager/home", "/admin/home" })
public class RoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        User u = (session != null) ? (User) session.getAttribute("user") : null;
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (path.startsWith("/admin") && u.getRoleId() != 3) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }
        if (path.startsWith("/manager") && u.getRoleId() != 2) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }
        if (path.startsWith("/user") && u.getRoleId() != 1) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }
        chain.doFilter(request, response);
    }
}
