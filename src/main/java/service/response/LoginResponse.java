package service.response;

import model.JWTToken;

public class LoginResponse {

    private final JWTToken token;

    public LoginResponse(JWTToken token) {
        this.token = token;
    }

    public JWTToken getToken() {
        return token;
    }
}
