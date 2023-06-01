package com.example.demo.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/currency/{currencyId}/delete", "/currency/{currencyId}/edit","/currency?success","/currency/new","/rate/{currencyId}/new","/rate/{currencyId}").hasAuthority("ADMIN")
                .requestMatchers("/login", "/register", "/currency/{currencyId}", "/currency", "/search","/currency?success",
                        "/currency/{currencyId}/exchange-rate/week","/currency/{currencyId}/exchange-rate/month","/currency/{currencyId}/exchange-rate/year",
                        "/currency/{currencyId}/exchange-rate/today","/currency/{currencyId}/exchange-rate","/register/**", "/css/**", "/js/**")
                .permitAll()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/currency")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

