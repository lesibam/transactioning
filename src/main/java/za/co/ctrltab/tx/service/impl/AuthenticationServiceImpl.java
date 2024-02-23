package za.co.ctrltab.tx.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.ctrltab.tx.dto.Role;
import za.co.ctrltab.tx.dto.auth.AuthenticationRequest;
import za.co.ctrltab.tx.dto.auth.AuthenticationResponse;
import za.co.ctrltab.tx.dto.auth.RegistrationRequest;
import za.co.ctrltab.tx.dto.auth.UserMapper;
import za.co.ctrltab.tx.persistence.entity.AppUser;
import za.co.ctrltab.tx.persistence.repository.UserRepository;
import za.co.ctrltab.tx.service.AuthenticationService;
import za.co.ctrltab.tx.service.JwtService;
import za.co.ctrltab.tx.service.exception.TransactioningException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserMapper mapper;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(final RegistrationRequest request) {
        log.info("User registration : [{}]", request.username());

        var user = AppUser.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .createdTimestamp(LocalDateTime.now())
                .passwordExpiryTimestamp(LocalDateTime.now().plusMonths(6))
                .disabled(false)
                .locked(false)
                .roles(List.of(Role.USER))
                .build();

        try {
            AppUser save = userRepository.save(user);
            log.trace("User registered {}", save.getId());
            var jwt = jwtService.generateToken(mapper.map(user));
            return AuthenticationResponse.builder().token(jwt).build();
        } catch (final Exception e) {
            log.error("Could not register user", e);
            String message = "Failed to register user";
            if (e instanceof DataIntegrityViolationException) {
                message = "User already registered.";
            }
            throw new TransactioningException(message, HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public AuthenticationResponse authenticate(final AuthenticationRequest request) throws TransactioningException {
        log.debug("Authenticating user: {}", request.username());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        } catch (final Exception e) {
            log.error("Unable to authenticate user [{}].", request.username(), e);
            throw new TransactioningException("Unable to authenticate.", HttpStatus.FORBIDDEN);
        }

        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new TransactioningException("Invalid username or password", HttpStatus.UNAUTHORIZED));
        var jwt = jwtService.generateToken(mapper.map(user));

        return AuthenticationResponse.builder().token(jwt).build();
    }
}
