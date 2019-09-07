package com.epm.recipe.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.epm.recipe.domain.user.AuthenticationToken;
import com.epm.recipe.domain.user.UserIdentity;
import com.epm.recipe.service.AuthenticationService;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class DefaultAuthenticationService implements AuthenticationService {

    private final Clock clock;
    private final Algorithm algorithm;
    private final Duration validFor;
    private final JWTVerifier verifier;

    public DefaultAuthenticationService(Clock clock, String secret, Duration validFor) {
        this.clock = Objects.requireNonNull(clock);
        this.algorithm = Algorithm.HMAC512(Objects.requireNonNull(secret));
        this.validFor = Objects.requireNonNull(validFor);
        this.verifier = ((com.auth0.jwt.JWTVerifier.BaseVerification) JWT.require(algorithm).acceptLeeway(1))
                .build(() -> Date.from(Instant.now(clock)));
    }

    @Override
    public AuthenticationToken generateToken(Long userId) {
        Instant now = Instant.now(clock);
        String token = JWT.create()
                .withIssuer("Recipe")
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(validFor)))
                .withSubject(String.valueOf(userId))
                .sign(algorithm);
        return new AuthenticationToken(token, validFor);
    }

    @Override
    public UserIdentity resolveUserId(AuthenticationToken token){
        DecodedJWT decoded = verifier.verify(token.token);
        long userId = Long.parseLong(decoded.getSubject());
        return new UserIdentity(userId);
    }
}
