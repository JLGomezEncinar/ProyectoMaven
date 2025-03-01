package iessanalberto.dam1.proyectomaven.services;

public class LoginService {
    private static final int maxAttempts = 3;

    public int getFailedAttempts() {
        return failedAttempts;
    }

    int failedAttempts = 0;


    public boolean isLogin (String user, String password) {
        boolean result = true;
        if (isLocked()) {
            result = false;

        } else {
            if (user.isEmpty() || user.trim().isEmpty()) {
                throw new IllegalArgumentException ("El usuario no puede estar vacío.");
            }
            if (password.isEmpty() || password.trim().isEmpty()) {
                throw new IllegalArgumentException ("El password no puede estar vacío.");
            }
        if(!(user.equals("user") && password.equals("password"))) {
            failedAttempts++;
            result = false;
        } else {
            failedAttempts = 0;
        }
        }

        return result;
    }
    public boolean isLocked() {
        return failedAttempts == maxAttempts;
    }
}
