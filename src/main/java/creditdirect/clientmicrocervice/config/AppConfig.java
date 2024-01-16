package creditdirect.clientmicrocervice.config;

import creditdirect.clientmicrocervice.entities.Client;
import creditdirect.clientmicrocervice.entities.Compte;
import creditdirect.clientmicrocervice.repositories.ClientRepository;
import creditdirect.clientmicrocervice.repositories.CompteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final ClientRepository clientRepo;
    private final CompteRepository compteRepo;

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> this.clientRepo.findByEmail(email);
    }

    @Bean
    public UserDetailsService compteDetailsService() {
        return nin -> this.compteRepo.findByNin(nin);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        //DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //authenticationProvider.setUserDetailsService(userDetailsService());
        //authenticationProvider.setPasswordEncoder(passwordEncoder());
        //return authenticationProvider;
        AuthenticationProvider provider = new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                log.info("user authenticating");
                String identifier = authentication.getName();
                String password = authentication.getCredentials().toString();
                log.info("credentials " + identifier + " " + password);
                Client client = (Client) userDetailsService().loadUserByUsername(identifier);
                //log.info("user "+user.getUsername()+" "+user.getPassword());
                if (client != null && passwordEncoder().matches(password, client.getPassword())) {
                    return new UsernamePasswordAuthenticationToken(identifier, password, client.getAuthorities());
                } else {
                    Compte compte = (Compte) compteDetailsService().loadUserByUsername(identifier);
                    if (compte != null && passwordEncoder().matches(password, compte.getPassword())) {
                        return new UsernamePasswordAuthenticationToken(identifier, password,compte.getAuthorities());
                    }
                }
                throw new BadCredentialsException("bad username or password");
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        };
        return provider;
    }

    @Bean
    public AuthenticationManager authManger(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(authenticationProvider());
        return builder.build();
    }

    /*
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


