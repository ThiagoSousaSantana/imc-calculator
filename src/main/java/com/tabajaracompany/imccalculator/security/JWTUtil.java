package com.tabajaracompany.imccalculator.security;

import com.tabajaracompany.imccalculator.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {

  private String jwtSecret = "q1HQy5rVN3n459IraYQVUWhOlO";

  private Long expiration = 86400000l;

  public String generateToken(Authentication authentication) {
    var user = (User) authentication.getPrincipal();
    return Jwts.builder()
        .setIssuer("tabajaracompany")
        .setSubject(user.getId().toString())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS256, jwtSecret)
        .compact();
  }

  public boolean tokenValid(String token) {
    try {
      Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public UUID getIdUser(String token) {
    var claims = Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody();
    return UUID.fromString(claims.getSubject());
  }
}
