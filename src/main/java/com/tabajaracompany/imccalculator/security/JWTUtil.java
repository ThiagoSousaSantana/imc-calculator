package com.tabajaracompany.imccalculator.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

  private String jwtSecret = "q1HQy5rVN3n459IraYQVUWhOlO";

  private Long expiration = 86400000l;

  public String generateToken(String userName) {
    return Jwts.builder()
        .setSubject(userName)
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.ES512, jwtSecret.getBytes(StandardCharsets.UTF_8))
        .compact();
  }

  public boolean tokenValid(String token) {
    var claims = getClaims(token);
    if (claims != null) {
      var userName = claims.getSubject();
      var expirationDate = claims.getExpiration();
      var now = new Date(System.currentTimeMillis());
      if (userName != null && expirationDate != null && now.before(expirationDate)) {
        return true;
      }
    }
    return false;
  }

  private Claims getClaims(String token) {
    try {
      return Jwts.parser()
          .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
          .parseClaimsJws(token)
          .getBody();
    } catch (Exception e) {
      return null;
    }
  }

  public String getUsername(String token) {
    var claims = getClaims(token);
    if (claims != null) {
      return claims.getSubject();
    }
    return null;
  }
}
