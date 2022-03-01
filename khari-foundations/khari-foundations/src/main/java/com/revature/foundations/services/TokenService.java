package com.revature.foundations.services;

import com.revature.foundations.util.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import netscape.security.Principal;

import java.util.Date;

public class TokenService {

    private JwtConfig jwtConfig;

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public <JwtBuilder> String generateToken(Principal subject) {

        // number of milliseconds passed since the beginning of UNIX time
        // start of UNIX time: January 1, 1970
        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                                      .setId(subject.getUser_id())
                                      .setIssuer("foundations")
                                      .setIssuedAt(new Date(now))
                                      .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                      .setSubject(subject.getUsername())
                                      .claim("role_id", subject.getRole_id())
                                      .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();

    }

    public Principal extractRequesterDetails(String token) {

        try {

            Claims claims = Jwts.parser()
                                .setSigningKey(jwtConfig.getSigningKey())
                                .parseClaimsJws(token)
                                .getBody();

            Principal principal = new Principal();
            principal.setUser_id(claims.getUser_id());
            principal.setUsername(claims.getSubject());
            principal.setRole_id(claims.get("role_id", String.class));

            return principal;

        } catch (Exception e) {
            return null;
        }

    }

}
