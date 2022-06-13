package com.example.demo.Security;
import com.example.demo.Services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SecurityService securityService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        // super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
               // .antMatchers("/manager/v1/home").hasAuthority("admin")
                .antMatchers("/manager/v1/admin/**").hasAuthority("admin")
                .antMatchers(HttpMethod.GET,"/manager/v1/userFolder/**/**").authenticated()
                .antMatchers("/initiate_db","/manager/v1/userFolder/all/1").permitAll()
                .antMatchers(HttpMethod.GET,"/manager/v1/userFolder/search/**").permitAll()
                .anyRequest().permitAll();
        http.httpBasic().and().logout().permitAll().logoutUrl("http://localhost:8081/logout");;

        //http.formLogin();
        // super.configure(http);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthpro = new DaoAuthenticationProvider();
        daoAuthpro.setUserDetailsService(securityService);
        daoAuthpro.setPasswordEncoder(noOpPasswordEncoder());
        return daoAuthpro;
    }

    @Bean
    NoOpPasswordEncoder noOpPasswordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
