package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ruslan", "Mingazov", (byte) 26);
        userService.saveUser("Sasha", "Mamin", (byte) 36);
        userService.saveUser("Timofey", "Papin", (byte) 12);
        userService.saveUser("Valera", "Porohnya", (byte) 56);
        userService.getAllUsers()
                .forEach(e -> System.out.println(e.toString()));
        userService.cleanUsersTable();
        userService.dropUsersTable();





    }
}
