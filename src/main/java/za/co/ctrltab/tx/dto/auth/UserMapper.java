package za.co.ctrltab.tx.dto.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import za.co.ctrltab.tx.persistence.entity.AppUser;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserMapper {

    public UserDetails map(final AppUser user) {
        log.info("Mapping user {} is User Expired [{}]", user.getUsername(), user.getPasswordExpiryTimestamp().isBefore(LocalDateTime.now()));
        return User.builder()
                .username(user.getUsername())
                .accountExpired(isBefore(user))
                .authorities(user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.name())).collect(Collectors.toList()))
                .credentialsExpired(isBefore(user))
                .accountLocked(user.isLocked())
                .password(user.getPassword())
                .disabled(user.isDisabled())
                .build();
    }

    private static boolean isBefore(AppUser user) {
        return user.getPasswordExpiryTimestamp().isBefore(LocalDateTime.now());
    }
}
