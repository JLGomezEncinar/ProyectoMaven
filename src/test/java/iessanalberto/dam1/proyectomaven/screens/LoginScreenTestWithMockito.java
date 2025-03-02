package iessanalberto.dam1.proyectomaven.screens;

import iessanalberto.dam1.proyectomaven.models.Usuario;
import iessanalberto.dam1.proyectomaven.services.UsuarioService;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginScreenTestWithMockito {
    @Mock
    private UsuarioService usuarioServiceMock;

    @Mock
    private Usuario usuarioMock;

    private LoginScreen loginScreen;

    @BeforeAll
    public static void initToolkit() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {
            });
        }
    }

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        loginScreen = new LoginScreen();
        loginScreen.setUsuarioService(usuarioServiceMock);
    }

    @Test
    public void testLoginExitoso() throws SQLException {
        // Dado
        String usuario = "user";
        String password = "password";
        usuarioMock = new Usuario(usuario, password);
        when(usuarioServiceMock.searchUser(usuario, password)).thenReturn(usuarioMock);
        when(usuarioServiceMock.isLogin(usuario, password)).thenReturn(true);

        loginScreen.getTxtUser().setText(usuario);
        loginScreen.getTxtPassword().setText(password);

        // Cuando
        loginScreen.getBtnLogin().fire();


        verify(usuarioServiceMock).searchUser(usuario, password);
        verify(usuarioServiceMock).isLogin(usuario, password);
    }
}

