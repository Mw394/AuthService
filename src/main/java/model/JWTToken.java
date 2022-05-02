package model;

import io.smallrye.jwt.build.Jwt;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class JWTToken {

    private final String token;

    public JWTToken(SecurityInfo securityInfo) { this(securityInfo, 60*60);}

    public JWTToken(SecurityInfo securityInfo, int age) {
        this.token = Jwt.issuer("http://com.waenglund")
                .subject(securityInfo.getUserid().getRaw())
                .preferredUserName(securityInfo.getUsername().getValue())
                .expiresIn(age)
                .issuedAt(LocalDateTime.now().toInstant(ZoneOffset.UTC))
                .sign();
    }

    public String getToken() { return token; }
}
