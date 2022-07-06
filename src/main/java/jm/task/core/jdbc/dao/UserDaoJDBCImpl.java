package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public void createUsersTable() {
        try (Connection connect = Util.getConnection();
             PreparedStatement statement = connect
                     .prepareStatement("CREATE TABLE IF NOT EXISTS users(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), lastName VARCHAR(50), age INT(3));")) {
            statement.executeUpdate();
            System.out.println("������� �������");
        } catch (SQLException e) {
            System.out.println("������, ������� ��� ����������");
        }

    }

    public void dropUsersTable() {
        try (Connection connect = Util.getConnection();
             PreparedStatement statement = connect.prepareStatement("DROP TABLE IF EXISTS users;")) {
            statement.executeUpdate();
            System.out.println("������� ������� ��� �� �� ������������");


        } catch (SQLException e) {
            System.out.println("������, ������� �� ����������");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connect = Util.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO users(name, lastName, age) " + "VALUES(?, ?, ?);")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User � ������ - %s �������� � ���� ������\n", name);
        } catch (SQLException e) {
            System.out.println("������ ������ � ���� ������");
        }

    }

    public void removeUserById(long id) {
        try (Connection connect = Util.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM users WHERE id = ?;")) {
            preparedStatement.setLong(2, id);
            preparedStatement.execute();
            System.out.printf("User � ������� id - %s ������ �� ���� ������\n", id);
        } catch (SQLException e) {
            System.out.println("������� �� ����������");
        }

    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        try (Connection connect = Util.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM users;")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString(3), resultSet.getByte(4));
                System.out.println(user);
                listUser.add(user);
            }
        } catch (SQLException sqlE) {
            System.out.println("������ ������ �� ���� ������");
        }
        return listUser;
    }

    public void cleanUsersTable() {
        try (Connection connect = Util.getConnection();
             PreparedStatement statement = connect.prepareStatement("TRUNCATE TABLE users;")) {
            statement.executeUpdate();
            System.out.println("������� �������");
        } catch (SQLException e) {
            System.out.println("������� �����������");
        }

    }

}
