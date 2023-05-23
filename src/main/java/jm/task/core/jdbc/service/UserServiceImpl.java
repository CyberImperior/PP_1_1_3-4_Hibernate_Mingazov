package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    UserDao userDao = new UserDaoJDBCImpl();
    public void createUsersTable() {
        userDao.createUsersTable();
        logger.info("Таблица успешно создана");
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        logger.info("Таблица успешно удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        logger.info("Пользователь " + name + " успешно добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        logger.info("Пользователь с id = " + id + " удален");
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        logger.info("Все пользователи успешно удалены");
    }
}
