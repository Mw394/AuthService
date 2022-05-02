package service;

import model.*;
import model.exception.NoUserException;
import model.exception.Sid;
import repository.Repository;
import resources.dto.DTOSignUp;
import service.request.LoginRequest;
import service.request.SignUpRequest;
import service.response.LoginResponse;
import service.response.SignUpResponse;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class AuthenticationService {

    private final Repository repository;

    @Inject
    public AuthenticationService(Repository repository) {
        this.repository = repository;
    }

    public SignUpResponse signup(SignUpRequest request) {
        User user = repository.createUser(new DTOSignUp(request.getEmail(), request.getUserName(), request.getPassword()));
        return new SignUpResponse(user.getEmail(), user.getUserName());
    }

    public LoginResponse login(LoginRequest request) throws NoUserException {
        User user = repository.findUser(request.getEmail(), request.getUserName(), request.getPassword());
        if (user == null) {
            throw new NoUserException(Sid.NO_SUCH_USER, String.format("Email: %s  does not exist", request.getUserName() != null ? request.getUserName(): "", request.getEmail() != null ? request.getEmail() : ""));
        }
        SecurityInfo securityInfo = new SecurityInfo(user.getId(), user.getUserName());
        JWTToken token = new JWTToken(securityInfo);
        return new LoginResponse(token);
    }
}
