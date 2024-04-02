package net.achraf.patientapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired

    private PasswordEncoder passwordEncoder;
    //System.out.println(encodedPass);


    /*@Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                return null;
            }
        };
    }*/
    @Bean

    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("12345")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("123456")).roles("USER","ADMIN").build()

                );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin().loginPage("/login").permitAll();
        httpSecurity.rememberMe();
        httpSecurity.authorizeHttpRequests().requestMatchers("/user/**","/user/index").hasRole("USER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**","/admin/deletePatient","/admin/save","/admin/editPatients","/admin/formPatients").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpSecurity.build();
    }
}
