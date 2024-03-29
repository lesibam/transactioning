package za.co.ctrltab.tx.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsernameFromToken(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
