package iessanalberto.dam1.proyectomaven;


import iessanalberto.dam1.proyectomaven.services.UsuarioService;
import javafx.application.Platform;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest extends JavaFXTestBase{

    private UsuarioService usuarioService;







    @BeforeEach
    void setUp () throws SQLException {





        usuarioService = new UsuarioService();

    }

    @Test
    void LoginSuccesful() {
        Platform.runLater(() -> {
           try {

                boolean result = usuarioService.isLogin("user", "password");
                Assertions.assertTrue(result, "Se debería loguear con estas credenciales");
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
        });
    }
    @Test
    void LoginBlocked () {
        Platform.runLater(() -> {
            try {
        Assertions.assertFalse(usuarioService.isLogin("wrongUser","wrongPassword"));
        Assertions.assertFalse(usuarioService.isLogin("user","wrongPassword"));
        Assertions.assertFalse(usuarioService.isLogin("wrongUser","password"));
        Assertions.assertFalse(usuarioService.isLogin("user","password"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Test
    void ResetAttempts () {
        Platform.runLater(() -> {
            try {
        Assertions.assertFalse(usuarioService.isLogin("wrongUser","wrongPassword"));
        Assertions.assertTrue(usuarioService.isLogin("user","password"));
        Assertions.assertEquals(0, usuarioService.getFailedAttempts());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Test
    void NullLogin () {
        Platform.runLater(() -> {
            try {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.isLogin("", "password");
        });
        assertEquals("El usuario no puede estar vacío.", exception.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


}