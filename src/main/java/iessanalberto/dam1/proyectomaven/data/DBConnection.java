package iessanalberto.dam1.proyectomaven.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static String URL;
    private static String USER;
    private static String PASSWORD;
     static {
         try (
                 InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
             if (input == null) {
                 System.out.println("No se pudo encontrar el archivo db.properties");

             }


             Properties prop = new Properties();
             try {
                 prop.load(input);
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }


             URL = prop.getProperty("db.url");
             USER = prop.getProperty("db.user");
             PASSWORD = prop.getProperty("db.password");




         } catch (IOException ex) {
             ex.printStackTrace();
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