package com.bootcampjava.negreirajeremy_pruebatec4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter() {
        return new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
            @Override
            public void configure(HttpSecurity http) throws Exception {
                http
                        .authorizeRequests()
                        // Historias de usuario de Hoteles
                        .requestMatchers("/agency/hotels/**").permitAll()
                        // Historias de usuario de Vuelos
                        .requestMatchers("/agency/flights/**").permitAll()
                        // Historias de Usuario generales (requieren autenticación)
                        .requestMatchers("/agency/hotels/new", "/agency/hotels/edit/**", "/agency/hotels/delete/**", "/agency/hotels/**").authenticated()
                        .requestMatchers("/agency/flights/new", "/agency/flights/edit/**", "/agency/flights/delete/**", "/agency/flights/**").authenticated()
                        .and()
                        .formLogin(Customizer.withDefaults());
            }
        };
    }
}
