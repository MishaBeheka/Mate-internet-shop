package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User("Vladimir",
                "Dolik",
                "Kyiv",
                "222-22-22",
                "user",
                "111");
        user.addRole(Role.of("USER"));
        userService.create(user);

        User admin = new User("Taras",
                "Lopin",
                "Odessa",
                "777-77-77",
                "admin",
                "222");
        admin.addRole(Role.of("ADMIN"));
        userService.create(admin);
        //resp.sendRedirect(req.getContextPath() + "/servlet/index");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }
}
