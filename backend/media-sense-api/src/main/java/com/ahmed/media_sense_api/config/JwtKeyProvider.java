package com.ahmed.media_sense_api.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;

@Component
public class JwtKeyProvider {

    @Value("${JWT_SECRET}")
    private String secret;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(decodedKey);
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
