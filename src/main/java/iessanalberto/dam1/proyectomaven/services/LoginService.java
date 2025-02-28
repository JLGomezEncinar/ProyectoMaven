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
        if(!(user.equals("user") && password.equals("password"))) {
            failedAttempts++;
            result = false;
        }
        }
        return result;
    }
    public boolean isLocked() {
        return failedAttempts == maxAttempts;
    }
}
