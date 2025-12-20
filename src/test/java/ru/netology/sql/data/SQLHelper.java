package ru.netology.sql.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static String getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes WHERE created = (SELECT max(created) FROM auth_codes);";

        try (var conn = getConnection()) {
            return runner.query(conn, codeSQL, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static void deleteTestDataFromCardTransaction() {
        var deleteQuery = "DELETE FROM auth_codes;";

        try (var conn = getConnection()) {
            runner.update(conn, deleteQuery);
        }
    }

    @SneakyThrows
    public static void deleteTestDataFromCards() {
        var deleteQuery = "DELETE FROM cards;";

        try (var conn = getConnection()) {
            runner.update(conn, deleteQuery);
        }
    }

    @SneakyThrows
    public static void deleteTestDataFromAuthCodes() {
        var deleteQuery = "DELETE FROM auth_codes;";

        try (var conn = getConnection()) {
            runner.update(conn, deleteQuery);
        }
    }

    @SneakyThrows
    public static void deleteTestDataFromUsers() {
        var deleteQuery = "DELETE FROM users;";

        try (var conn = getConnection()) {
            runner.update(conn, deleteQuery);
        }
    }
}
