package com.example.bank_system.Config;


import com.example.bank_system.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> auth

    .requestMatchers(HttpMethod.POST, "/api/v1/customer/register").permitAll()

   
    .requestMatchers("/api/v1/user/**").hasAuthority("ADMIN")

    .requestMatchers(HttpMethod.GET,    "/api/v1/customer").hasAuthority("ADMIN")
    .requestMatchers(HttpMethod.GET,    "/api/v1/customer/**").hasAuthority("ADMIN")
    .requestMatchers(HttpMethod.PUT,    "/api/v1/customer").hasAuthority("CUSTOMER")
    .requestMatchers(HttpMethod.DELETE, "/api/v1/customer/**").hasAuthority("ADMIN")

    // EMPLOYEE
    .requestMatchers(HttpMethod.POST,   "/api/v1/employee/register").hasAuthority("ADMIN")
    .requestMatchers(HttpMethod.GET,    "/api/v1/employee/**").hasAuthority("ADMIN")
    .requestMatchers(HttpMethod.PUT,    "/api/v1/employee/**").hasAuthority("ADMIN")
    .requestMatchers(HttpMethod.DELETE, "/api/v1/employee/**").hasAuthority("ADMIN")

    // ACCOUNT
    .requestMatchers(HttpMethod.GET,    "/api/v1/account/my-accounts").hasAuthority("CUSTOMER")
    .requestMatchers(HttpMethod.GET,    "/api/v1/account").hasAuthority("ADMIN")
    .requestMatchers(HttpMethod.GET,    "/api/v1/account/**").hasAnyAuthority("ADMIN","EMPLOYEE")
    .requestMatchers(HttpMethod.POST,   "/api/v1/account").hasAuthority("EMPLOYEE")
    .requestMatchers(HttpMethod.PUT,    "/api/v1/account/activate/**").hasAuthority("EMPLOYEE")
    .requestMatchers(HttpMethod.PUT,    "/api/v1/account/block/**").hasAuthority("EMPLOYEE")
    .requestMatchers(HttpMethod.PUT,    "/api/v1/account/deposit/**").hasAuthority("CUSTOMER")
    .requestMatchers(HttpMethod.PUT,    "/api/v1/account/withdraw/**").hasAuthority("CUSTOMER")
    .requestMatchers(HttpMethod.PUT,    "/api/v1/account/transfer").hasAuthority("CUSTOMER")
    .requestMatchers(HttpMethod.PUT,    "/api/v1/account/**").hasAuthority("CUSTOMER")
    .requestMatchers(HttpMethod.DELETE, "/api/v1/account/**").hasAuthority("CUSTOMER")

    .anyRequest().authenticated()
)
                .httpBasic(ttpBasic -> {
                });

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
