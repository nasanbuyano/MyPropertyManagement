package com.miu.propertymanagement.integration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.function.Function;

public class Jwt {

   private String SECRET_KEY = "MySecretKeyMySecretKeyMySecretKey";
   private SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");

   public String generateToken(String username, String role) {
       return Jwts.builder()
               .subject(username)
               .claim("role", role)
               .issuedAt(new Date(System.currentTimeMillis()))
               .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
               .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
               .compact();
   }

   public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
           return true;
       } catch (Exception e) {
           return false;
       }
   }

   public String getUserNameFromToken(String token) {
       return getClaimFromToken(token, Claims::getSubject);
    }

   public String getRoleFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("role", String.class));
   }

   private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        return claimsResolver.apply(claims);
   }
}
