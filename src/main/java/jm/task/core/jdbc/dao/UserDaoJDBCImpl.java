package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String SQL = "START TRANSACTION; CREATE TABLE IF NOT EXISTS Users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), lastName VARCHAR(45), age TINYINT); COMMIT;";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("START TRANSACTION; DROP TABLE IF EXISTS Users; COMMIT;")){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("START TRANSACTION;");
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement2 =
                    connection.prepareStatement("INSERT INTO Users VALUES (default, ?, ?, ?);");
            preparedStatement2.setString(1, name);
            preparedStatement2.setString(2, lastName);
            preparedStatement2.setByte(3, age);
            preparedStatement2.executeUpdate();

            PreparedStatement preparedStatement3 =
                    connection.prepareStatement("COMMIT;");
            preparedStatement3.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("START TRANSACTION;");
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement2 = Util.getConnection().prepareStatement("DELETE FROM Users WHERE id=?");
            preparedStatement2.setLong(1, id);
            preparedStatement2.executeUpdate();

            PreparedStatement preparedStatement3 =
                    connection.prepareStatement("COMMIT;");
            preparedStatement3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = Util.getConnection().createStatement()) {
            String SQL = "START TRANSACTION; SELECT * FROM Users; COMMIT;";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка SQL!");
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("START TRANSACTION; DELETE FROM Users; COMMIT;")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
