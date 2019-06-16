package nlu.edu.cdhttt.demo.config;

import nlu.edu.cdhttt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new UserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/posts").permitAll()
                .antMatchers(
                        "/",
                        "/js/**",
                        "/css/**",
                        "/imgs/**",
                        "/vendor/**",
                        "/webjars/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/dashboard/**").hasAuthority("ADMIN")
                .antMatchers("/write/**").hasAuthority("ADMIN")
                .antMatchers("/edit/**").hasAuthority("ADMIN")
                .antMatchers("/save/**").hasAuthority("ADMIN")
                .antMatchers("/post/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/login").failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .and()
                .authorizeRequests();


    }

    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        authenticationMgr
                .userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
