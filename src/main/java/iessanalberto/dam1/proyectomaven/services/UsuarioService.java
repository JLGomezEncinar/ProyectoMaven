package iessanalberto.dam1.proyectomaven.services;

import iessanalberto.dam1.proyectomaven.data.DBConnection;
import iessanalberto.dam1.proyectomaven.models.Usuario;
import iessanalberto.dam1.proyectomaven.repositories.UsuarioDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

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


    public boolean isLogin (String user, String password) throws SQLException {
        boolean result = true;
        if (isLocked()) {
            result = false;

        } else {
            if (user.isEmpty() || user.trim().isEmpty()) {
                throw new IllegalArgumentException ("El usuario no puede estar vacío.");
            }
            if (password.isEmpty() || password.trim().isEmpty()) {
                throw new IllegalArgumentException ("El password no puede estar vacío.");
            }
            if(searchUser(user, password) == null) {
                failedAttempts++;
                result = false;
            } else {
                failedAttempts = 0;
            }
        }
        System.out.println(result);
        return result;

    }
    public boolean isLocked() {
        return failedAttempts == maxAttempts;
    }

}
