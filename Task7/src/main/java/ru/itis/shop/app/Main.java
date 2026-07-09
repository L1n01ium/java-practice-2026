package ru.itis.shop.app;

import ru.itis.shop.infrastructure.persistence.jdbc.DriverManagerDataSource;
import ru.itis.shop.user.api.UserConsoleOperations;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;
import ru.itis.shop.user.repository.UserRepository;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        Properties properties = new Properties();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (inputStream == null) {
                System.out.println("Файл не найден");
                return;
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        String url = properties.getProperty("db_url");
        String user = properties.getProperty("db_user");
        String password = properties.getProperty("db_password");

        DataSource dataSource = new DriverManagerDataSource(url, user, password);
        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);

        UserService userService = new UserService(userRepository);

        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}
