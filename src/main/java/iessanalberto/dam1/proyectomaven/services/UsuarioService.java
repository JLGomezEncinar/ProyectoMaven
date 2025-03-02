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

}
