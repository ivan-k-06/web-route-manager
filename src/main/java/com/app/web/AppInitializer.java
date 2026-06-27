package com.app.web;

import com.app.db.*;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DatabaseConfig config = DatabaseConfig.load();
            DatabaseManager databaseManager = new DatabaseManager(config);
            new SchemaInitializer(databaseManager).initialize();

            UserRepository userRepository = new UserRepository(databaseManager);
            RouteRepository routeRepository = new RouteRepository(databaseManager);

            sce.getServletContext().setAttribute("userRepository", userRepository);
            sce.getServletContext().setAttribute("routeRepository", routeRepository);

            System.out.println("Сервер запущен. База данных готова!");
        } catch (Exception e) {
            System.err.println("Ошибка при старте сервера: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}