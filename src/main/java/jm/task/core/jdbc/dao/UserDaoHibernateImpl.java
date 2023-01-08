package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;
    private Transaction transaction = null;

    public UserDaoHibernateImpl () {
        this.sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); // начинаем транзакцию

            String SQL = "CREATE TABLE IF NOT EXISTS Users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), lastName VARCHAR(45), age TINYINT);";  // создаём переменную с текстом запроса

            NativeQuery<?> nativeQuery = session.createNativeQuery(SQL); // создаём объект с параметром текста запроса SQL

            nativeQuery.executeUpdate();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); // начинаем транзакцию

            String SQL = "DROP TABLE IF EXISTS Users;";  // создаём переменную с текстом запроса

            NativeQuery<?> nativeQuery = session.createNativeQuery(SQL); // создаём объект с параметром текста запроса SQL

            nativeQuery.executeUpdate();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
