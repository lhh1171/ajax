package com.ease.architecture.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {

    public static <T> void returnWrapper(HttpServletResponse response, T returnObject) throws IOException {
        String s = new Gson().toJson(returnObject);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(s);
        response.getWriter().flush();
    }
}
