package com.example.demo.security;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByUsername(username);
        if(user != null){
            User authUser = new User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
            return authUser;
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
