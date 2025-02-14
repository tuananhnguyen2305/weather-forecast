package com.nta.utils;


import com.nta.cms.constants.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtils {
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    static  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(JwtConstant.JWT_SECRET).build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return JwtConstant.JWT_TOKEN_PREFIX + createToken(claims, userDetails.getUsername());
    }

    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return JwtConstant.JWT_TOKEN_PREFIX + createToken(claims, username);
    }

    private static String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.JWT_EXPIRATION))
            .signWith(SignatureAlgorithm.HS256, JwtConstant.JWT_SECRET).compact();
    }

    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
