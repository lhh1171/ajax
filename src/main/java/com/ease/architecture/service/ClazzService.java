package com.ease.architecture.service;

import com.ease.architecture.dao.ClazzDao;
import com.ease.architecture.entity.Clazz;

import java.util.List;

public class ClazzService {

    private ClazzDao clazzDao = new ClazzDao();

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            ClazzDao.insertClazz(new Clazz("clazz" + (i + 1), (i + 1) + "班"));
        }
        List<Clazz> clazz = ClazzDao.findClazzByPage(90000, 10);
        System.out.println(clazz);
    }


    public List<Clazz> findClazzByPage(int start, int size) {
        return clazzDao.findClazzByPage(start, size);
    }
}
