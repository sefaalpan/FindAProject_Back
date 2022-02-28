package be.ros.FindAFreelance.configs.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


import be.ros.FindAFreelance.models.entities.User;
import static be.ros.FindAFreelance.configs.SecurityConstants.*;

@Component
public class JwtTokenProvider {
    
    private final UserDetailsService service;

    public JwtTokenProvider(UserDetailsService service)
    {
        this.service = service;
    }

    public String createToken(User user)
    {
        return TOKEN_PREFIX + JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .withClaim("roles", user.getRoles())
            .sign(Algorithm.HMAC512(JWT_KEY));
    }

    public String resolveToken(HttpServletRequest request)
    {
        String token = request.getHeader(HEADER_KEY);

        if(token != null && token.startsWith(TOKEN_PREFIX))
        {
            // Supprime le Bearer du token.
            return token.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateToken(String token)
    {
        try 
        {
            DecodedJWT decoded = JWT.require(Algorithm.HMAC512(JWT_KEY))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""));

            String username = decoded.getSubject();
            Date exp = decoded.getExpiresAt();

            return username != null && exp != null && exp.after(new Date(System.currentTimeMillis()));
        }
        catch(JWTVerificationException e)
        {
            return false;
        }
    }

    public Authentication getAuthentication(String token)
    {
        String username = JWT.decode(token.replace(TOKEN_PREFIX, "")).getSubject();
        UserDetails userDetails = service.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }
}
