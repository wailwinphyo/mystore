package com.codex.mystore.config;

import com.codex.mystore.dao.impl.MyUserDetailsIImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfig {

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private MyUserDetailsIImpl userDetailsService;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private TeamAuthorityFilter teamAuthorityFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/register/**").permitAll()
                .antMatchers("/api/users/updatePassword").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/users/secured").hasRole("ADMIN")
                .antMatchers("/api/users/editUser").hasRole("ADMIN")
                .antMatchers("/api/users/allUser").hasAnyRole("ADMIN", "LEADER")
                .antMatchers("/api/role/createRole").hasRole("ADMIN")
                .antMatchers("/api/role/project/createProject").hasRole("ADMIN")
                .antMatchers("/api/guest/create").hasRole("ADMIN")
                .antMatchers("/api/task/**").hasAnyRole("ADMIN", "LEADER", "USER")
                .antMatchers("/api/guest/**").hasAnyRole("ADMIN", "GUEST")

                .antMatchers("/api/project/developer").hasAnyAuthority("MOBILE_DEVELOPER")
                .antMatchers(HttpMethod.DELETE, "/api/project/*").hasAnyAuthority("TASK_DELETE")
                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(teamAuthorityFilter, AuthTokenFilter.class);

        return http.build();
    }
}
