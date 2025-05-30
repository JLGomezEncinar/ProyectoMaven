package iessanalberto.dam1.proyectomaven;

import iessanalberto.dam1.proyectomaven.screens.LoginScreen;
import iessanalberto.dam1.proyectomaven.services.UsuarioService;
import javafx.application.Platform;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginScreenTest extends JavaFXTestBase {

    @Mock
    private UsuarioService usuarioServiceMock;

    @Mock
    private TextField txtUserMock;

    @Mock
    private PasswordField txtPasswordMock;

    private LoginScreen loginScreen;

    @BeforeEach
    public void setUp() throws SQLException {
        loginScreen = new LoginScreen();
        loginScreen.setUsuarioService(usuarioServiceMock);
        loginScreen.setTxtUser(txtUserMock);
        loginScreen.setTxtPassword(txtPasswordMock);
    }

    @Test
    public void testLoginExitoso() throws SQLException, InterruptedException {
        String usuario = "user";
        String password = "password";

        when(usuarioServiceMock.isLogin(usuario, password)).thenReturn(true);
        when(txtUserMock.getText()).thenReturn(usuario);
        when(txtPasswordMock.getText()).thenReturn(password);

        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                loginScreen.getBtnLogin().fire();
                verify(usuarioServiceMock).isLogin(usuario, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }
}
