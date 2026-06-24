package com.journalapp.journalApp.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
@Value("${SECRET_KEY}")
private String secretKey;
    public String generateToken(String userName){
        Map<String,String >claims=new HashMap<>();
        return createToken(claims,userName);
    }
    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String, String> claims, String subject) {
        return Jwts.builder().claims(claims).subject(subject).header().empty().add("typ","jwt").and().issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()+1000*60*60)).signWith(getSigningKey()).compact();
    }


    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
