package iessanalberto.dam1.proyectomaven.repositories;

import iessanalberto.dam1.proyectomaven.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public Usuario searchUser(String user, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM USUARIOS WHERE USER = ? AND PASSWORD = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(user, password);
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException("No se ha podido realizar la consulta");
        }
        return usuario;
    }
}
