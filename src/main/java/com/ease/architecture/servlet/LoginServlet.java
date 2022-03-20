package com.ease.architecture.servlet;

import com.ease.architecture.service.UserServiceImpl;
import com.ease.architecture.utils.DesUtil;
import com.ease.architecture.utils.ResponseUtil;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {


    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        req.getCookies();

        //使用username,password查询相应的用户是否存在于数据库中
        //如果存在，返回hello,如果不存在提示错误
//        resp.setCharacterEncoding("UTF-8");
        String passMd5 = DigestUtils.md5Hex(password);
        if (userService.findUserByNameAndPassword(username, passMd5) == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.getWriter().write("用户不存在或密码错误");
            return;
        }
        Cookie cookie = new Cookie("userInfo", DesUtil.encrypt(username+ DesUtil.split +password));
        cookie.setMaxAge(1800);

        resp.addCookie(cookie);
        ResponseUtil.returnWrapper(resp,"hello"+username);
    }
}
