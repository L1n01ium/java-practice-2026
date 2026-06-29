package shop.app;

import shop.user.api.UserConsoleOperations;
import shop.user.infrastructure.UserDatabaseRepository;
import shop.user.infrastructure.UserFileRepository;

public class Main {
    public static void main(String[] args) {
        UserFileRepository userFileRepository = new UserFileRepository("users.txt");
        UserDatabaseRepository userDatabaseRepository = new UserDatabaseRepository();
        UserConsoleOperations operations = new UserConsoleOperations(userFileRepository);
        while (true) {
            operations.showMenu();
        }
    }
}
