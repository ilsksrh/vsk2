package com.example.library.service;

import com.example.library.models.AppUser;
import com.example.library.models.Permission;
import com.example.library.repository.PermissionRepo;
import com.example.library.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final PermissionRepo permRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepo.findByEmail(email);
        if(Objects.nonNull(user)) {
            return user;
        }

        throw new UsernameNotFoundException("User Not Found");
    }

    public void register(AppUser user, PasswordEncoder passwordEncoder) {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Permission userRole = permRepo.findByName("ROLE_USER");
        if (userRole == null) throw new RuntimeException("role not found");
        user.setPermissions(List.of(userRole));
        userRepo.save(user);
    }
}
