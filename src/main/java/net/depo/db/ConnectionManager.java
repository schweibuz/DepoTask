package net.depo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by andmat on 2019-08-18.
 */
public class ConnectionManager implements DbContract {
    static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try {
                con = DriverManager.getConnection(HOST + DB_NAME + MOSCOW_TIME_ZONE,
                        USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
