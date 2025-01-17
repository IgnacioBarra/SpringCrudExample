package MotorcycleRepo.CrudExample.Controller;
import MotorcycleRepo.CrudExample.Entity.UserEntity;
import MotorcycleRepo.CrudExample.Repository.RoleRepository;
import MotorcycleRepo.CrudExample.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return new ResponseEntity<String>("Username not available. Choose a different one.", HttpStatus.BAD_REQUEST);
        }else{
            //encrypt password before storing
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            //An account created by a user shouldn't have higher credentials thant just a user.
            user.setRoles(Arrays.asList(roleRepository.findByName("USER").get()));
            UserEntity createdUser = userRepository.save(user);
            return new ResponseEntity<String>("User created successfully", HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
    }


}
