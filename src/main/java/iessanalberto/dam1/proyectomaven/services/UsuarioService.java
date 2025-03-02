package iessanalberto.dam1.proyectomaven.services;

import iessanalberto.dam1.proyectomaven.alerts.AlertClass;
import iessanalberto.dam1.proyectomaven.data.DBConnection;
import iessanalberto.dam1.proyectomaven.models.Usuario;
import iessanalberto.dam1.proyectomaven.repositories.UsuarioDAO;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    private Usuario usuario;

    public UsuarioService() throws SQLException {

        Connection connection = DBConnection.obtenerConexion();
        this.usuarioDAO = new UsuarioDAO(connection);
    }
    public Usuario searchUser(String user, String password) throws SQLException {
        return usuarioDAO.searchUser(user,password);
    }
    private static final int maxAttempts = 3;

    public int getFailedAttempts() {
        return failedAttempts;
    }

    int failedAttempts = 0;

    AlertClass alertClass = new AlertClass();

    public void isLogin (String user, String password) throws SQLException {
        usuario = usuarioDAO.searchUser(user, password);
        if (isLocked()) {

            alertClass.showAlert(Alert.AlertType.ERROR,"Usuario Bloqueado","Se han superado el número de intentos.");
        } else if (user.isEmpty() || user.trim().isEmpty()) {
                //throw new IllegalArgumentException ("El usuario no puede estar vacío.");
                alertClass.showAlert(Alert.AlertType.ERROR,"Faltan datos","El usuario no puede estar vacío.");
            } else if (password.isEmpty() || password.trim().isEmpty()) {
                //throw new IllegalArgumentException ("El password no puede estar vacío.");
                alertClass.showAlert(Alert.AlertType.ERROR,"Faltan datos","El password no puede estar vacío.");
            } else if (usuario == null) {
                failedAttempts++;

                alertClass.showAlert(Alert.AlertType.ERROR,"Datos erróneos","Usuario y/o contraseña incorrectos");
            } else {
                failedAttempts = 0;
                alertClass.showAlert(Alert.AlertType.INFORMATION,"Conexión exitosa","Se ha logueado correctamente.");
            }



    }
    public boolean isLocked() {
        return failedAttempts == maxAttempts;
    }

}
