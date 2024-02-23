package za.co.ctrltab.tx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.co.ctrltab.tx.dto.auth.UserMapper;
import za.co.ctrltab.tx.persistence.repository.UserRepository;
import za.co.ctrltab.tx.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).map(mapper::map)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
