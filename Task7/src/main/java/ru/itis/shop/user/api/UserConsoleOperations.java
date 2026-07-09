package ru.itis.shop.user.api;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.domain.User;

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
            case "1": signUp(); break;
            case "2": signIn(); break;
            case "3": findUserById(); break;
            case "4": updateProfile(); break;
            case "5": showAllUsers(); break;
            case "6": findUsersByDescription(); break;
            case "0": System.exit(0);
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание пользователя по почте");
        System.out.println("5. Получить информацию обо всех пользователях");
        System.out.println("6. Показать информацию о пользователях с заданным описанием профиля");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите name:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);
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

    private void findUserById() {
        System.out.println("Введите id пользователя:");
        String idStr = scanner.nextLine();
        try {
            int id = Integer.parseInt(idStr);
            UserDto userDto = userService.findUserDtoById(id);
            if (userDto != null) {
                System.out.println("ID: " + userDto.getId());
                System.out.println("Имя: " + userDto.getName());
                System.out.println("Email: " + userDto.getEmail());
                System.out.println("Описание: " + userDto.getProfileDescription());
            } else {
                System.out.println("Пользователь с таким id не найден");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateProfile() {
        System.out.println("Обновить описание профиля");
        System.out.println("Введите email пользователя:");
        String email = scanner.nextLine();
        System.out.println("Введите новое описание профиля:");
        String newDescription = scanner.nextLine();

        boolean updated = userService.updateProfileByEmail(email, newDescription);
        if (updated) {
            System.out.println("Профиль обновлен");
        } else {
            System.out.println("Пользователь с таким email не найден");
        }
    }

    private void showAllUsers() {
        List<UserDto> users = userService.findAllUsers();
        if (users.isEmpty()) {
            System.out.println("Пользователей нет");
        } else {
            System.out.println("Список пользователей:");
            for (UserDto user : users) {
                System.out.println("ID: " + user.getId() + ", Имя: " + user.getName() + ", Email: " + user.getEmail() + ", Описание: " + user.getProfileDescription());
            }
        }
    }

    private void findUsersByDescription() {
        System.out.println("Введите описание профиля для поиска:");
        String description = scanner.nextLine();
        List<UserDto> users = userService.findAllUsersByProfileDescription(description);
        if (users.isEmpty()) {
            System.out.println("Пользователей с таким описанием нет");
        } else {
            System.out.println("Найденные пользователи:");
            for (UserDto user : users) {
                System.out.println("ID: " + user.getId() + ", Имя: " + user.getName() + ", Email: " + user.getEmail());
            }
        }
    }

}
