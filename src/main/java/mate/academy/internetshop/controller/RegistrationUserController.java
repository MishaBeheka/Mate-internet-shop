package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class RegistrationUserController extends HttpServlet {

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registrationUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User(req.getParameter("first_name"),
                req.getParameter("last_name"),
                req.getParameter("address"),
                req.getParameter("phone"),
                req.getParameter("email"),
                req.getParameter("psw"));
        User user = userService.create(newUser);

        HttpSession session = req.getSession(true);
        session.setAttribute("userId", user.getUserId());

        Cookie cookie = new Cookie("MATE", user.getToken());
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/servlet/index");
    }
}
