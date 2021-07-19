package com.tabajaracompany.imccalculator.security;

import com.tabajaracompany.imccalculator.repository.UserRepository;
import com.tabajaracompany.imccalculator.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecuritySetting extends WebSecurityConfigurerAdapter {

  private static final String[] PUBLIC_MATCHERS_POST = {"/user/**", "/auth"};
  private static final String[] PUBLIC_MATCHERS = {"/h2-console/**"};

  @Autowired private Environment environment;
  @Autowired private UserDetailsServiceImpl userDetailsService;
  @Autowired private JWTUtil jwtUtil;
  @Autowired private UserRepository userRepository;

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
      httpSecurity.headers().frameOptions().disable();
    }

    httpSecurity
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST)
        .permitAll()
        .antMatchers(PUBLIC_MATCHERS)
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(
            new JWTAuthenticationFilter(jwtUtil, userRepository),
                UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("*/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
