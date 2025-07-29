package com.tsinsi.auth.configuration;

import com.tsinsi.auth.application.out.UserPersistencePort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthDetailService implements UserDetailsService {

    private final UserPersistencePort userPersistencePort;

    public AuthDetailService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPersistencePort.findByUsername(username).map(user -> {
                    UserDetails userDetails = User.builder()
                            .username(user.getUsername())
                            .password(user.getPassword())
                            .authorities(Collections.emptyList())
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build();
                    AuthUser authUser = new AuthUser(userDetails, user.getId());
                    authUser.setNickname(user.getNickname());
                    authUser.setGender(user.getGender());
                    return authUser;
                }
        ).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
