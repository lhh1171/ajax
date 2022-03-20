package com.ease.architecture.dao;

import com.ease.architecture.entity.User;
import com.ease.architecture.utils.MysqlConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {


    public User findByUserId(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        User user = null;

        try {
            connection = MysqlConnectionUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM t_user_1 WHERE id=?");
            preparedStatement.setString(1, id);
            set = preparedStatement.executeQuery();
            if (set != null && set.next()) {
                user = new User();
                user.setId(set.getString(1));
                user.setName(set.getString(2));
                return user;
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
        return user;
    }


    public User findByName(String name) {
        Class c = UserDao.class;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        User user = null;

        try {
            connection = MysqlConnectionUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM t_user_1 WHERE name=?");
            preparedStatement.setString(1, name);
            set = preparedStatement.executeQuery();
            if (set != null && set.next()) {
                user = new User();
                user.setId(set.getString(1));
                user.setName(set.getString(2));
                return user;
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
        return user;
    }

    public User findByNameAndPassword(String name, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        User user = null;

        try {
            connection = MysqlConnectionUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM t_user_1 WHERE name=? AND password=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            set = preparedStatement.executeQuery();
            if (set != null && set.next()) {
                user = new User();
                user.setId(set.getString(1));
                user.setName(set.getString(2));
                return user;
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
        return user;
    }

    public int insertUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MysqlConnectionUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO t_user_1 (id,name,password) VALUES (?,?,?)");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
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
}
