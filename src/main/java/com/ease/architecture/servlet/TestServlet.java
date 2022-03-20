package com.ease.architecture.servlet;

import com.ease.architecture.entity.Clazz;
import com.ease.architecture.service.ClazzService;
import com.ease.architecture.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TestServlet extends HttpServlet {
    private ClazzService clazzService = new ClazzService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int start = Integer.parseInt(req.getParameter("start"));
        int size = Integer.parseInt(req.getParameter("size"));
        List<Clazz> clazzList = clazzService.findClazzByPage(start, size);
        System.out.println(start);
        ResponseUtil.returnWrapper(resp, clazzList);
    }
}
