package MotorcycleRepo.CrudExample.Security;

import MotorcycleRepo.CrudExample.Entity.RoleEntity;
import MotorcycleRepo.CrudExample.Entity.UserEntity;
import MotorcycleRepo.CrudExample.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        List<String> userRoles = user.getRoles().stream().map(RoleEntity::getName).toList();
        return User.builder().username(user.getUsername()).password(user.getPassword()).roles(userRoles.toArray(new String[0])).build();
    }
}
