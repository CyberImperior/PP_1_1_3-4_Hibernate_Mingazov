package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private EntityTransaction entityTransaction;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            String SQL = "create table if not exists users(id int AUTO_INCREMENT PRIMARY KEY, name varchar(25), lastName varchar(25), age int)";
            session.createNativeQuery(SQL).executeUpdate();

            entityTransaction = session.getTransaction();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive() && entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }


    }

    @Override
    public void dropUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            String SQL = "drop table if exists users";
            session.createNativeQuery(SQL).executeUpdate();

            entityTransaction = session.getTransaction();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive() && entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.persist(user);

            entityTransaction = session.getTransaction();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive() && entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.remove(user);

            entityTransaction = session.getTransaction();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive() && entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            String HQL = "select e from User e";
            list = session.createQuery(HQL).getResultList();

            entityTransaction = session.getTransaction();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive() && entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            String HQL = "DELETE FROM User";
            session.createQuery(HQL).executeUpdate();

            entityTransaction = session.getTransaction();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive() && entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
