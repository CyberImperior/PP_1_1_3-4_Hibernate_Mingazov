package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String SQL = "create table if not exists users(id int AUTO_INCREMENT PRIMARY KEY, name varchar(25), lastName varchar(25), age int)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String SQL = "drop table if exists users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
        preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "insert into users (name, lastName, age) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL = "delete from users where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SQL = "select * from users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "truncate table users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
