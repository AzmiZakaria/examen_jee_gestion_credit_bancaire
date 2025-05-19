package ma.enset.bdcc.azmi.examen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/**")
                .permitAll()
                .anyRequest()
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}