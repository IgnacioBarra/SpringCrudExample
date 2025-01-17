package MotorcycleRepo.CrudExample.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//the whole purpose of this class is to avoid the circular dependency that comes from including this bean in the RestSecurityConfig class
@Configuration
public class PasswordEncoderBean {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
