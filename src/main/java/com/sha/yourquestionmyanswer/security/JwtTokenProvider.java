package com.sha.yourquestionmyanswer.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String APP_SECRET;
    @Value("${jwt.expires.in}")
    private long EXPIRES_IN;

    public String generateJwtToken(Authentication authentication) {
        //principal = user to we will authenticate in user object
       JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
       Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);

       return Jwts.builder()
               .setSubject(Long.toString(userDetails.getId()))
               .setIssuedAt(new Date())
               .setExpiration(expireDate)
               .signWith(SignatureAlgorithm.HS512, APP_SECRET)
               .compact();
    }

    Long getUserIdFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(APP_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    boolean validateToken(String token) {
      try {
          Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
          return !isTokenExpired(token);
      } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
               IllegalArgumentException e) {
            return false;
      }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(APP_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
