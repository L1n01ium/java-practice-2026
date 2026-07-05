package shop.app;

import shop.user.api.UserConsoleOperations;
import shop.user.application.UserService;
import shop.user.infrastructure.persistence.UserFileRepository;
import shop.user.infrastructure.persistence.UserMapper;
import shop.user.infrastructure.persistence.UserRepositoryJdbcImpl;
import shop.user.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryJdbcImpl();
        UserService userService = new UserService(userRepository);
        UserConsoleOperations operations = new UserConsoleOperations(userService);
        while (true) {
            operations.showMenu();
        }
    }
}
