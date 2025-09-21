package com.tsinsi.auth.adapter.in.security;

import com.tsinsi.auth.application.out.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userPersistencePort;

    public CustomUserDetailService(UserRepository userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPersistencePort.findByUsername(username).map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
