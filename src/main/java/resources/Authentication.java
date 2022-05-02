package resources;

import model.Email;
import model.Password;
import model.UserName;
import model.exception.BadRequestException;
import model.exception.DomainException;
import model.exception.NoUserException;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import repository.Repository;
import resources.dto.DTOLoggedIn;
import resources.dto.DTOUser;
import service.AuthenticationService;

import org.jboss.logging.Logger;
import service.request.LoginRequest;
import service.request.SignUpRequest;
import service.response.LoginResponse;
import service.response.SignUpResponse;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Authentication {
    private final AuthenticationService service;
    private JsonWebToken jsonWebToken;
    private final Logger logger;
    private final Repository rep;

    @Inject
    public Authentication(AuthenticationService service, Logger logger, Repository repository, JsonWebToken jsonWebToken) {
        this.service = service;
        this.jsonWebToken = jsonWebToken;
        this.logger = logger;
        this.rep = repository;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        logger.info("TestCall");
        return "HelloRestEasy";
    }


    @POST
    @PermitAll
    @Path("/signUp")
    public SignUpResponse signUp(DTOUser user) {
            System.out.println(user.getEmail() + " " + user.getUserName() + " " + user.getPassword());
            SignUpResponse signUpResponse = service.signup(new SignUpRequest(new Email(user.getEmail()), new UserName(user.getUserName()), new Password(user.getPassword())));
            return signUpResponse;
    }

    @POST
    @PermitAll
    @Path("/login")
    public Response login(DTOUser user) {
        System.out.println(user);
        try {
            LoginResponse loginResponse = service.login(new LoginRequest(new Email(user.getEmail()), new UserName(user.getUserName()), new Password(user.getPassword())));
            return Response.ok()
                    .cookie(createCookie(60 * 60, loginResponse.getToken().getToken())).entity(new DTOLoggedIn(true, user.getEmail())).build();
        } catch (NoUserException e) {
            return Response.status(400).build();
        }
    }

    @GET
    @Path("/logout")
    @PermitAll
    public Response logout() {
        return Response.ok().cookie(createLogoutCookie()).entity(new DTOLoggedIn(false, null)).build();
    }

    @GET
    @Path("/loggedIn")
    @PermitAll
    public DTOLoggedIn loggedIn() {
        String userName = jsonWebToken.getClaim(Claims.preferred_username);
        if (userName != null) {
            return new DTOLoggedIn(true, jsonWebToken.getClaim(Claims.preferred_username));
        }
        return new DTOLoggedIn(false, jsonWebToken.getClaim(Claims.preferred_username));
    }

    private NewCookie createCookie(int age, String token) {
        return new NewCookie("jwt", token, "/", "localhost", "comment", age + 3600, false, true);
    }

    private NewCookie createLogoutCookie() {
        return new NewCookie("jwt", null, "/", "localhost", 0, "comment", 0, new Date(0), false, true);
    }

}

