package br.com.fiap.moverakiapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
            .authorizeHttpRequests()
             
                .antMatchers(HttpMethod.GET, "/api/usuario/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/usuario/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/usuario/**").permitAll()
              
                .antMatchers(HttpMethod.GET, "/api/transporte/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/transporte/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/transporte/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/transporte/**").permitAll()

                .antMatchers(HttpMethod.POST, "/api/entrar").permitAll()

                .antMatchers("/h2-console/**").permitAll()

                .antMatchers(HttpMethod.GET,"/transporte/**").permitAll()
                .antMatchers(HttpMethod.GET,"/transporte/delete/**").permitAll()
                .antMatchers(HttpMethod.POST,"/transporte/**").permitAll()

                .antMatchers("/css/**").permitAll()

                .anyRequest().denyAll()
            .and()
                .csrf().disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //.and()
                .headers().frameOptions().disable()
            .and()
                .formLogin()
                //.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            ;
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
