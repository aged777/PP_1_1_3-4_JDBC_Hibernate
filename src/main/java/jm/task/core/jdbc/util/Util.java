package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static String urlDB = "jdbc:mysql://localhost:3306/mydbstudy";
    private static String userName  = "root1";
    private static String password = "root";

    public static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(urlDB, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
    // реализуйте настройку соеденения с БД
}
