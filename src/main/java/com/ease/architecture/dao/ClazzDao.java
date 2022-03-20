package com.ease.architecture.dao;

import com.ease.architecture.entity.Clazz;
import com.ease.architecture.utils.MysqlConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClazzDao {
    public static int insertClazz(Clazz clazz) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlConnectionUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO t_clazz (clazz_name,clazz_desc) VALUES (?,?)");
            preparedStatement.setString(1, clazz.getClazzName());
            preparedStatement.setString(2, clazz.getClazzDesc());
            return preparedStatement.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


    public static List<Clazz> findClazzByPage(int start, int size) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        List<Clazz> clazzList = new ArrayList<>();

        try {
            connection = MysqlConnectionUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM t_clazz limit ?,?;");
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, size);
            set = preparedStatement.executeQuery();

            while (set != null && set.next()) {
                Clazz clazz = new Clazz();
                clazz.setId(set.getInt(1));
                clazz.setClazzName(set.getString(2));
                clazz.setClazzDesc(set.getString(3));
                clazzList.add(clazz);
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return clazzList;
    }

//    public static void main(String[] args) {
//        System.out.println(findClazzByPage(10,10));
//    }
}
