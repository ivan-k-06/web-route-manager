package com.app.db;

public record DatabaseConfig(String host, String database, String user, String password) {
    public static DatabaseConfig load() {
        String user = System.getenv().getOrDefault("DB_USER", "admin");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "secret123");
        String host = System.getenv().getOrDefault("DB_HOST", "localhost");
        String database = System.getenv().getOrDefault("DB_NAME", "routes_db");

        return new DatabaseConfig(host, database, user, password);
    }

    public String jdbcUrl() {
        return "jdbc:postgresql://" + host + ":5432/" + database;
    }
}