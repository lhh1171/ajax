package com.ease.architecture.servlet;

import com.ease.architecture.entity.User;
import com.ease.architecture.service.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setName(username);
        user.setPassword(DigestUtils.md5Hex(password));
//        HttpSession session = req.getSession();
//        session.setAttribute("a", 1);
        boolean register = userService.register(user);
        if (register) {
            resp.getWriter().print("hello" + user.getName());
//            req.getRequestDispatcher("test").forward(req, resp);
            resp.sendRedirect("http://localhost:8080/test");
            return;
        }
        resp.getWriter().print("注册失败");

    }
}
