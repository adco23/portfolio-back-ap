package com.portfolio.AC.security.jwt;

import com.portfolio.AC.security.entity.UsuarioPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
  private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

  @Value("${jwt.secret}")
  private String secret;
  @Value("${jwt.expiration}")
  private Integer expiration;

  public String generateToken (Authentication authentication) {
    UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();

    return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime()+expiration*1000))
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  public String getUsernameFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch (MalformedJwtException error) {
      logger.error("Token malformado");
    } catch (UnsupportedJwtException error) {
      logger.error("Token no soportado");
    } catch (ExpiredJwtException error) {
      logger.error("Token expirado");
    } catch (IllegalArgumentException error) {
      logger.error("Token vacio");
    } catch (SignatureException error) {
      logger.error("Firma no válida");
    }
    return false;
  }
}
