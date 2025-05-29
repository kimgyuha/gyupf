package com.example.gyupf.config;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import com.example.gyupf.admin.dto.AdminResponseDto;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	private static final String SECRET_KEY = "my-very-secret-key-which-is-very-very-secure123!";

	public static String generateToken(AdminResponseDto admin) {
	    byte[] keyBytes = SECRET_KEY.getBytes();
	    Key key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

	    return Jwts.builder()
	        .setSubject(admin.getUsername())
	        .claim("name", admin.getName())
	        .claim("id", admin.getId())
	        .claim("role", "admin")
	        .setIssuedAt(new Date())
	        .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
	        .signWith(key, SignatureAlgorithm.HS256)
	        .compact();
	}



    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
