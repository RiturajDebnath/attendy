package com.attendy.attendy.service;

import com.attendy.attendy.dto.LoginRequest;
import com.attendy.attendy.entity.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final StudentService studentService;
    private static final String SECRET_KEY = "9J5H9705gABGQ7McnT09Lq9aV4eQshzAJieM26WhWBhkFBZCnxc4V0EZynkkJm0u1LfH0a6RsmG";

    private SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Autowired
    public AuthServiceImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<Object> verifyToken(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No cookies found.");
        }

        for (Cookie cookie : cookies) {
            if ("AttendyRest".equals(cookie.getName())) {
                String JwtToken = cookie.getValue();
                try {
                    Jws<Claims> claims = Jwts.parser()
                            .verifyWith(key)
                            .build()
                            .parseSignedClaims(JwtToken);

                    // Extract relevant information from claims (user email)
                    String userEmail = claims.getPayload().getSubject();

                    System.out.println(userEmail);

                    Student student = studentService.findByEmail(userEmail);
                    if (student == null) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
                    }

                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Login successful");
                    response.put("isStudent", true);
                    response.put("isTeacher", false);
                    response.put("user", student);
                    return ResponseEntity.ok().body(response);
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No JWT cookie found.");
    }

    @Override
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        Student student = studentService.findByEmail(loginRequest.getEmail());

        if (student == null) {
            return ResponseEntity.notFound().build();
        } else if (!student.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        String JwtToken = generateJwtToken(student.getEmail());

        // Create an HTTP-only cookie with the JWT token
        ResponseCookie cookie = ResponseCookie.from("AttendyRest", JwtToken)
                .httpOnly(true)
                .maxAge(3600 * 24 * 30 * 2) // 2 Months in seconds
                .path("/")
                .build();

        // Add the cookie to the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("isStudent", true);
        response.put("isTeacher", false);
        return ResponseEntity.ok().headers(headers).body(response);

        // return ResponseEntity.ok().body("Login successful");
    }

    private String generateJwtToken(String userEmail) {
        long expirationTimeInMs = 3600000L * 24 * 30 * 2; // 1 hour * 1 Day * 30 Days * 2 Months = 2 Months
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTimeInMs);

        return Jwts.builder()
                .subject(userEmail)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key, Jwts.SIG.HS512)
                .compact();
    }
}
