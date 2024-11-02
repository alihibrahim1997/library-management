package com.maids.cc.library.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

@Component
public class TokenFilter extends OncePerRequestFilter {
	private static final String SECRET_KEY = "7cd89a4694a1b7bba3e8dbf9af967f4f172d373b3c5c0a4e1a78f72f263a52366d8c7e8c378804df23d7aa0f914ecef6bb942d7ba9c012f00709baefe0b49f89";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");


  
            if (token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }

            boolean isValidToken = this.validateToken(token);

            if (isValidToken) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    

    public boolean validateToken(String token) {
        try {
            Map<String, String> envVar = System.getenv();
           // String SECRET_KEY = envVar.get("SECRET_KEY");
     
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            String username = decodedJWT.getSubject();

            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }
}
