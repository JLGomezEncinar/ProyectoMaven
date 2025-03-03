package iessanalberto.dam1.proyectomaven.navigation;

import iessanalberto.dam1.proyectomaven.models.Usuario;
import iessanalberto.dam1.proyectomaven.screens.LoginScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Navigation {
    public static Stage stage = new Stage();

    //Creamos los diferentes navegadores para las escenas
    public static void navigate(String destination) throws SQLException {
        switch (destination) {
            case "LoginScreen" -> {
                LoginScreen loginScreen = new LoginScreen();
                Scene loginScene = new Scene(loginScreen.getRoot(), 320, 240);
                stage.setTitle("Conexión");
                stage.setScene(loginScene);
                stage.show();
            }

        }
    }
    public static void navigate(String destination, Usuario usuario) throws SQLException {
        switch (destination) {
            case "SecondScreen" -> {
                LoginScreen loginScreen = new LoginScreen();
                Scene loginScene = new Scene(loginScreen.getRoot(), 320, 240);
                stage.setTitle("Segunda pantalla");
                stage.setScene(loginScene);
                stage.show();
            }

        }
    }
}