package iessanalberto.dam1.proyectomaven.screens;


import iessanalberto.dam1.proyectomaven.services.UsuarioService;
import javafx.application.Platform;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginScreenTestWithMockito {
    @Mock
    private UsuarioService usuarioServiceMock;


    @Mock
    private TextField txtUserMock;

    @Mock
    private PasswordField txtPasswordMock;

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
        loginScreen.setTxtUser(txtUserMock);
        loginScreen.setTxtPassword(txtPasswordMock);


    }

    @Test
    public void testLoginExitoso() throws SQLException {

        String usuario = "user";
        String password = "password";

        when(usuarioServiceMock.isLogin(usuario, password)).thenReturn(true);
        when(txtUserMock.getText()).thenReturn(usuario);
        when(txtPasswordMock.getText()).thenReturn(password);

        loginScreen.getBtnLogin().fire();


        verify(usuarioServiceMock).isLogin(usuario, password);
    }
}

