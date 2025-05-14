package iessanalberto.dam1.proyectomaven.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
     static {
         try (
                 InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
             if (input == null) {
                 throw new Exception("No se pudo encontrar el archivo db.properties");

             }


             Properties prop = new Properties();
             try {
                 prop.load(input);
             } catch (IOException e) {
                 throw new RuntimeException("Error al cargar el archivo");
             }


             URL = prop.getProperty("db.url");
             USER = prop.getProperty("db.user");
             PASSWORD = prop.getProperty("db.password");




         } catch (Exception ex) {
             throw new RuntimeException("No se pudo realizar la conexión");
         }
     }


    public static Connection obtenerConexion() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Error al conectar a la base de datos", e);
        }
    }
}