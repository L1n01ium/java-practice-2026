package shop.app;

import shop.user.api.UserConsoleOperations;
import shop.user.application.UserService;
import shop.user.infrastructure.persistence.UserFileRepository;
import shop.user.infrastructure.persistence.UserMapper;

public class Main {
    public static void main(String[] args) {
        UserFileRepository userFileRepository = new UserFileRepository("users.txt", new UserMapper());
        UserService userService = new UserService(userFileRepository);
        UserConsoleOperations operations = new UserConsoleOperations(userService);
        while (true) {
            operations.showMenu();
        }
    }
}
