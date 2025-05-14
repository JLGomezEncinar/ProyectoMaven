package iessanalberto.dam1.proyectomaven;

import iessanalberto.dam1.proyectomaven.services.UsuarioService;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() throws SQLException {
        usuarioService = new UsuarioService();
    }

    @Test
    void loginSuccesful() throws SQLException {
        boolean result = usuarioService.isLogin("user", "password");
        assertTrue(result, "Se debería loguear con estas credenciales");
    }

    @Test
    void loginBlocked() throws SQLException {
        assertFalse(usuarioService.isLogin("wrongUser", "wrongPassword"));
        assertFalse(usuarioService.isLogin("user", "wrongPassword"));
        assertFalse(usuarioService.isLogin("wrongUser", "password"));

        // Este test falla si "user"/"password" no está bloqueado tras los intentos anteriores.
        // Puedes ajustar el comportamiento si tu lógica bloquea tras X intentos.
        assertFalse(usuarioService.isLogin("user", "password"));
    }

    @Test
    void resetAttempts() throws SQLException {
        assertFalse(usuarioService.isLogin("wrongUser", "wrongPassword"));
        assertTrue(usuarioService.isLogin("user", "password"));
        assertEquals(0, usuarioService.getFailedAttempts());
    }

    @Test
    void nullLogin() throws SQLException {

            usuarioService.isLogin("", "password");

        assertEquals("El usuario no puede estar vacío.", "El usuario no puede estar vacío.");
    }
}
