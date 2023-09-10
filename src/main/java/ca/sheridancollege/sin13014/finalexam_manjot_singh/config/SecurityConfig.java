package ca.sheridancollege.sin13014.finalexam_manjot_singh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {




    @Bean

    public SecurityFilterChain filerChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        // Allow H2 console to be accessed in frames
        http.headers().frameOptions().sameOrigin();
        http.authorizeHttpRequests((auth) ->
                        auth

                                .requestMatchers(HttpMethod.GET, "/").permitAll()
                                .requestMatchers(HttpMethod.GET, "/view-programmers").permitAll()
                                .requestMatchers(HttpMethod.GET, "/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/add-programmer").hasAnyRole("BOSS")
                                .requestMatchers(HttpMethod.GET, "/programmers/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/programmers/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/programmers").permitAll()

                                .anyRequest().authenticated())
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login")
                                .failureUrl("/login?failed")
                                .permitAll())
                .logout((logout)->
                        logout
                                .deleteCookies("remove")
                                .invalidateHttpSession(true)
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll())
                .exceptionHandling((exceptionHandling)->
                        exceptionHandling
                                .accessDeniedPage("/accessDenied"));

        return http.build();
    }
    private UserDetailsServiceImpl userDetailsService;
    public SecurityConfig(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService =  userDetailsService;
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passEncoder) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passEncoder);
        return authenticationManagerBuilder.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
