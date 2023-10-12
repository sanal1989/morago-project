package com.habsida.moragoproject.configuration.security;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + phone);
        }
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), user.getRoles().stream().map(x->new SimpleGrantedAuthority("ROLE_"+x.getName().toString())).collect(Collectors.toList()));
    }
}
