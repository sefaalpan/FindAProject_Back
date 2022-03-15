package be.ros.FindAProject.configs;


import be.ros.FindAProject.configs.jwt.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtTokenProvider provider;

    public SecurityConfig(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET,
                        "/categories/**",
                        "/projets/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/clients/**",
                        "/freelances/**")
                .authenticated()
                .antMatchers(HttpMethod.POST,
                        "/projets/**",
                        "/clients/**",
                        "/freelances/**")
                .authenticated()
                .antMatchers(HttpMethod.DELETE,
                        "/projets/**")
                .authenticated()
                .antMatchers(HttpMethod.PUT,
                        "/freelances/**").authenticated()
                .antMatchers(HttpMethod.POST,
                        "/login",
                        "/register")
                .permitAll()
                .antMatchers(HttpMethod.DELETE,
                        "/clients/**",
                        "/freelances/**")
                .hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/categories/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/categories/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/is-logged").authenticated()
                .anyRequest().authenticated();


        http.addFilterBefore(new JwtAuthFilter(this.provider), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers()
                .frameOptions()
                .disable();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://localhost:4200/");
        config.addAllowedMethod("*"); // POST/GET/PUT/DELETE/OPTIONS/...
        config.addAllowedHeader("*");
        config.addExposedHeader("Authorization");
        config.setAllowCredentials(true);

        return request -> config;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
