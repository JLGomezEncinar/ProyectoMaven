package iessanalberto.dam1.proyectomaven.services;

public class LoginService {
    public boolean isLogin (String user, String password) {
        return user.equals("user") && password.equals("password");
    }
}
