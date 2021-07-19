package com.tabajaracompany.imccalculator.security;

import com.tabajaracompany.imccalculator.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

  private JWTUtil jwtUtil;

  private UserRepository userRepository;

  public JWTAuthenticationFilter(JWTUtil jwtUtil, UserRepository userRepository) {
    this.jwtUtil = jwtUtil;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var token = recoverToken(request);
    var tokenValid = jwtUtil.tokenValid(token);
    System.out.println(tokenValid);
    if (tokenValid) {
      authenticateUser(token);
    }

    filterChain.doFilter(request, response);
  }

  private void authenticateUser(String token) {
    var idUser = jwtUtil.getIdUser(token);
    var user = userRepository.findById(idUser).get();
    var authenticationToken =
        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
  }

  private String recoverToken(HttpServletRequest request) {
    var token = request.getHeader("Authorizationc");
    if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
      return null;
    }
    return token.substring(7, token.length());
  }
}
