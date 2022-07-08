package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Roman", "Black", (byte) 13);
        userService.saveUser("Oleg", "Fulba", (byte) 18);
        userService.saveUser("Adrianno", "Meressy", (byte) 21);
        userService.getAllUsers();
        userService.removeUserById(2L);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.getAllUsers();
        userService.dropUsersTable();
        userService.close();
    }
}
