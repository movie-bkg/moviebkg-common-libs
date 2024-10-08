package com.sid.moviebkg.common.token.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    public String extractUsername(String token, String secretKey) {
        return extractClaim(token, secretKey, Claims::getSubject);
    }

    public <T> T extractClaim(String token, String secretKey, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token, secretKey);
        return claimsResolver.apply(claims);
    }

    public <T> T extractClaim(String token, String secretKey, Function<Claims, T> claimsResolver, SignatureAlgorithm signatureAlgorithm) {
        final Claims claims = extractAllClaims(token, secretKey, signatureAlgorithm);
        return claimsResolver.apply(claims);
    }

    public String generateToken(String secretKey, UserDetails userDetails, long jwtExpiration) {
        return generateToken(secretKey, new HashMap<>(), userDetails, jwtExpiration);
    }

    private String generateToken(String secretKey, HashMap<String, Object> extraClaims, UserDetails userDetails,
                                 long jwtExpiration) {
        extraClaims.put("role", userDetails.getAuthorities().stream()
                .map(grantedAuthority -> "ROLE_" + grantedAuthority.getAuthority())
                .collect(Collectors.joining(",")));
        return buildToken(secretKey, extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(String token, String secretKey, long jwtExpiration) {
        Claims claims = extractAllClaims(token, secretKey);
        return Jwts.builder().claims().empty().add(claims).and()
                .subject(claims.getSubject())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    public String buildToken(String secretKey, Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts.builder().claims().add(extraClaims).and()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey(secretKey))
                .compact();
    }

    public boolean isTokenValid(String token, String secretKey) {
        return !isTokenExpired(token, secretKey);
    }

    public boolean isTokenValid(String token, String secretKey, SignatureAlgorithm signatureAlgorithm) {
        return !isTokenExpired(token, secretKey, signatureAlgorithm);
    }

    private boolean isTokenExpired(String token, String secretKey) {
        return extractExpiration(token, secretKey).before(new Date());
    }

    private boolean isTokenExpired(String token, String secretKey, SignatureAlgorithm signatureAlgorithm) {
        return extractExpiration(token, secretKey, signatureAlgorithm).before(new Date());
    }

    private Date extractExpiration(String token, String secretKey, SignatureAlgorithm signatureAlgorithm) {
        return extractClaim(token, secretKey, Claims::getExpiration, signatureAlgorithm);
    }

    private Date extractExpiration(String token, String secretKey) {
        return extractClaim(token, secretKey, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token, String secretKey) {
        return Jwts.parser()
                .verifyWith(getKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Claims extractAllClaims(String token, String secretKey, SignatureAlgorithm signatureAlgorithm) {
        return Jwts.parser()
                .verifyWith(getKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Key getSignInKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Key getSignInKey(String secretKey, SignatureAlgorithm signatureAlgorithm) {
        byte[] apiKeySecretBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }
}
