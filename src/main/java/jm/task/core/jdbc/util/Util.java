package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static String urlDB = "jdbc:mysql://localhost:3306/mydbstudy";
    private static String userName  = "root1";
    private static String password = "root";

    public static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
        }

        try {
            connection = DriverManager.getConnection(urlDB, userName, password);
        } catch (SQLException e) {
            System.out.println("Ошибка SQL при создании подключения!");;
        }
    }


    // реализуйте настройку соеденения с БД
}
