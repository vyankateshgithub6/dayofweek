package com.finastra.spi.dayofweek.security;

import com.auth0.jwk.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.finastra.spi.dayofweek.controller.DayOfWeekController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;

class SignatureValidator implements OAuth2TokenValidator<Jwt> {

    private final String issuer;
    private final String WELL_KNOWN_JWKS_PATH = "oidc/jwks.json";

    Logger logger = LoggerFactory.getLogger(DayOfWeekController.class);

    public SignatureValidator(String issuer) {
        this.issuer = issuer;
    }

    public OAuth2TokenValidatorResult validate(Jwt jwt) {

        try {
            Jwk jwk = null;
            JwkProvider provider = new UrlJwkProvider(new URL(issuer + "/" +  WELL_KNOWN_JWKS_PATH));
            DecodedJWT jwtDecoded = JWT.decode(jwt.getTokenValue());
            jwk = provider.get(jwtDecoded.getKeyId());
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
            algorithm.verify(jwtDecoded);
            return OAuth2TokenValidatorResult.success();
        } catch (JwkException jwkE) {
            logger.error("Could not validate signature: " + jwkE.getMessage());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Problem with retrieving jwks.json file");
        }

        OAuth2Error error = new OAuth2Error("invalid_token", "The required audience is missing", null);

        // Check expiration
        if (jwt.getExpiresAt().isBefore(Instant.now())){
            throw new RuntimeException("Expired token!");
        }

        return OAuth2TokenValidatorResult.failure(error);
    }
}
