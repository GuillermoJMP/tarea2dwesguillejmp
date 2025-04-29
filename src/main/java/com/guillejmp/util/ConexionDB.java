package com.guillejmp.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private static Connection conn;
    private static final String PROP = "/db.properties";

    private ConexionDB() { }

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try (InputStream in = ConexionDB.class.getResourceAsStream(PROP)) {
                Properties p = new Properties();
                p.load(in);
                Class.forName(p.getProperty("db.driver"));
                conn = DriverManager.getConnection(
                    p.getProperty("db.url"),
                    p.getProperty("db.user"),
                    p.getProperty("db.password")
                );
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC no encontrado.", e);
            } catch (Exception e) {
                throw new SQLException("Error cargando propiedades o conectando.", e);
            }
        }
        return conn;
    }
}