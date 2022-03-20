package com.ease.architecture.filter;

import com.ease.architecture.service.UserServiceImpl;
import com.ease.architecture.utils.DesUtil;
import com.ease.architecture.utils.ResponseUtil;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthFilter implements Filter {
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getRequestURI().contains("login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String username = "";
        String password = "";
        for (Cookie cookie : request.getCookies()) {
            if (!cookie.getName().equals("userInfo")) {
                continue;
            }
            String decrypt = DesUtil.decrypt(cookie.getValue());
            if (null == decrypt || decrypt.equals("")) {
                authFailed(response);
            }
            String[] split = decrypt.split(DesUtil.split);
            username = split[0];
            password = split[1];

        }
        if ("".equals(username) || "".equals(password)) {
            authFailed(response);
        }
        String passMd5 = DigestUtils.md5Hex(password);
        if (userService.findUserByNameAndPassword(username, passMd5) == null) {
            authFailed(response);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

    private void authFailed(HttpServletResponse response) throws IOException {
        Map<String, Object> failMap = new HashMap<>();
        failMap.put("message", "登录失败");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ResponseUtil.returnWrapper(response, failMap);
    }
}
