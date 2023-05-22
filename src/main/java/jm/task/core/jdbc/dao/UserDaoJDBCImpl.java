package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            String SQL = "create table users(id int AUTO_INCREMENT PRIMARY KEY, name varchar(25), lastName varchar(25), age int)";
            try {
                statement.execute(SQL);
                System.out.println("������� ������� �������");
            } catch (SQLException e) {
                System.out.println("�� ���������� ������� �������, ������ ��� ��� ��� ����������");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
        Statement statement = Util.getConnection().createStatement();
        String SQL = "drop table users";
            try {
                statement.execute(SQL);
                System.out.println("������� ������� �������");
            } catch (SQLException e) {
                System.out.println("�� ���������� ������� �������, ������ ��� ��� �� ���");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            String SQL = "insert into users (name, lastName, age) values (?, ?, ?)";
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            System.out.println("������������ " + name + " ������� �������� � ���� ������");

        } catch (SQLException e) {
            System.out.println("�� ���������� �������� ������������");
        }
    }

    public void removeUserById(long id) {
        String SQL = "delete from users where id = ?";
        try {
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            System.out.println("������������ � id = " + id + " ������");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = Util.getConnection().createStatement();
            String SQL = "select * from users";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
                System.out.println(user.toString() + " ������� ������� � ������");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            String SQL = "delete from users";
            statement.execute(SQL);
            System.out.println("��� ������������ ������� �������");
        } catch (SQLException e) {
            System.out.println("������ ������� �������");
        }

    }
}
