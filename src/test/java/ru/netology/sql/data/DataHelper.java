package ru.netology.sql.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    private static Faker faker;

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    public static String generateInvalidPassword(String locale) {
        faker = new Faker(new Locale(locale));

        return faker.internet().password();
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }
}
