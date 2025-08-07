package net.Myapp.journalApp.config;

import net.Myapp.journalApp.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import net.Myapp.journalApp.filter.JwtFilter;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private net.Myapp.journalApp.filter.JwtFilter jwtFilter;


 @Override
protected void configure(HttpSecurity http) throws Exception{
     http.authorizeRequests()
             .antMatchers("/journal/**","/users/**").authenticated()    // ** wild card entry
             .antMatchers("/admin/**").hasRole("ADMIN")
             .anyRequest().permitAll()
             .and()
             .httpBasic();
     http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
     http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
 }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImp).passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
