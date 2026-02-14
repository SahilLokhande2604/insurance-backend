//package com.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//import java.security.Key;
//
//public class JwtUtil {
//
//    private static final String SECRET = "your-secret-key-which-is-very-long";
//
//    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
//
//    public static String extractUsername(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        return claims.getSubject();
//    }
//}
