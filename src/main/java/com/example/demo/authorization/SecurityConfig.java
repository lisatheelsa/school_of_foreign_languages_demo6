package com.example.demo.authorization;

import com.example.demo.authorization.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // определяет настройки фильтрации запросов и правила авторизации для URL-адресов запросов, которые должны быть обработаны;
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/register").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/**").authenticated()
                .and()
                .exceptionHandling()
                // начало настройки обработки исключений при выполнении запросов
                // Forbidden – недостаточно прав для перехода на страницу
                .accessDeniedPage("/403")

                .and()
                .formLogin()
                    .loginPage("/login_page")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/login_page")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .and().build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        // определяет метод шифрования паролей, которые будут использоваться для авторизации пользователей;
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        // определяет класс, который будет использоваться для проверки подлинности пользователей (например, для проверки правильности пользовательского имени и пароля).
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
