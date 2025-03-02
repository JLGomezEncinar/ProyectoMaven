package iessanalberto.dam1.proyectomaven.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
    private LoginService loginService;

    @BeforeEach
    void setUp () {
        loginService = new LoginService();
    }
    @Test
    void LoginSuccesful() {
        boolean result = loginService.isLogin("user", "password");
        Assertions.assertTrue(result,"Se debería loguear con estas credenciales");
    }
    @Test
    void LoginBlocked () {
        Assertions.assertFalse(loginService.isLogin("wrongUser","wrongPassword"));
        Assertions.assertFalse(loginService.isLogin("user","wrongPassword"));
        Assertions.assertFalse(loginService.isLogin("wrongUser","password"));
        Assertions.assertFalse(loginService.isLogin("user","password"));
    }
    @Test
    void ResetAttempts () {
        Assertions.assertFalse(loginService.isLogin("wrongUser","wrongPassword"));
        Assertions.assertTrue(loginService.isLogin("user","password"));
        Assertions.assertEquals(0,loginService.getFailedAttempts());
    }
    @Test
    void NullLogin () {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            loginService.isLogin("", "password");
        });
        assertEquals("El usuario no puede estar vacío.", exception.getMessage());
    }


}