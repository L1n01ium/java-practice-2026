package shop.user.api;

import shop.user.application.UserService;
import shop.user.domain.User;

import java.util.List;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();
        switch (command) {
            case "1": {
                System.out.println("Сейчас будем регистрировать пользователя");
                signUp();
            } break;
            case "2": {
                signIn();
            } break;
            case "3": {
                findById();
            } break;
            case "4": {
                updateDescription();
            } break;
            case "5": {
                showAllUsers();
            } break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private void updateDescription() {
        System.out.println("Обновить профиль");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите новое описание профиля:");
        String newDescription = scanner.nextLine();
        boolean updated = userService.updateProfile(email, newDescription);
        if (updated) {
            System.out.println("Профиль обновлен");
        } else {
            System.out.println("Пользователь с таким email не найден");
        }
    }

    private void findById() {
        System.out.println("Поиск email пользователя по id");
        System.out.println("Введите id:");
        String id = scanner.nextLine();
        User foundUser = userService.findById(id);
        if (foundUser != null) {
            System.out.println("Email: " + foundUser.getEmail());
        } else {
            System.out.println("Пользователя с таким id не существует");
        }
    }

    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void signUp() {
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();
        userService.signUp(email, password, profileDescription);
    }

    private void showAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            System.out.println("Пользователей нет");
        } else {
            System.out.println("Список пользователей:");
            for (User user : users) {
                System.out.println("Email: " + user.getEmail() + ", Имя: " + user.getName());
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить профиль");
        System.out.println("5. Вывести всех пользователей");
        System.out.println("0. Выход");
    }
}
