package com.simsasookbak.global.config;

import com.simsasookbak.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{

//    @Bean
//    public WebSecurityCustomizer configure(){
//        return web -> web.ignoring().requestMatchers();
//    }//"/static/**","/img/**","/js/**","/lib/**","/scss/**","/css/**"

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        // InMemoryUserDetailsManager (see below)
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.authorizeHttpRequests(auth->auth.requestMatchers("/login","/member/register").permitAll()
                .anyRequest().authenticated())
                .formLogin(auth->auth.loginPage("/login").defaultSuccessUrl("/").permitAll())
                .logout(auth->auth.logoutSuccessUrl("/").invalidateHttpSession(true))
                .csrf(auth->auth.disable()).build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
