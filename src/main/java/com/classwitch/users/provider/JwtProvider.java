package com.classwitch.users.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JwtProvider {

    private final Key key;


    public JwtProvider(@Value("${secret.key}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String create(String email) {
        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder().signWith(key, SignatureAlgorithm.HS256).setSubject(email)
            .setExpiration(expireDate).compact();

        return jwt;
    }

    public String validate(String jwt){
        String subject= null;

        try {
            Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

            subject = claims.getSubject();
            return  subject;
        }catch (Exception exception){
            exception.printStackTrace();
            return subject;
        }

    }
}
