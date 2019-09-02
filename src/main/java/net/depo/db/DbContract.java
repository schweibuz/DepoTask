package net.depo.db;

/**
 * Created by andmat on 2019-08-17.
 */
public interface DbContract {
    String HOST = "jdbc:mysql://localhost:3306/";
    String DB_NAME = "depo";
    String MOSCOW_TIME_ZONE = "?serverTimezone=EST5EDT";
    String USERNAME = "root";
    String PASSWORD = "provider";
}
