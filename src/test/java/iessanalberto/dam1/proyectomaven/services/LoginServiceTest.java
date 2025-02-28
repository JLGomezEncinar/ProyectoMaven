package iessanalberto.dam1.proyectomaven.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    @Test
    void isLogin() {
        LoginService service = new LoginService();
        boolean result = service.isLogin("user", "password");
        Assertions.assertTrue(result,"Se debería loguear con estas credenciales");
    }
}