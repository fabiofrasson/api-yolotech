package br.com.yolotech.api_spring.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/yolotech", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
