package MotorcycleRepo.CrudExample.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class RestSecurityConfig {


    private CustomUserDetailsService userDetailsService;

    @Autowired
    public RestSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((authz)-> authz
                .requestMatchers(HttpMethod.GET, "/api/motorcycles").hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                .requestMatchers(HttpMethod.GET, "/api/motorcycles/**").hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                .requestMatchers(HttpMethod.POST, "/api/motorcycles").hasAnyRole( "ADMIN", "SUPERADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/motorcycles").hasAnyRole("ADMIN", "SUPERADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/motorcycles/**").hasRole("SUPERADMIN")
                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .anyRequest().denyAll())
                .httpBasic(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                .logout(LogoutConfigurer::permitAll);

        return httpSecurity.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
