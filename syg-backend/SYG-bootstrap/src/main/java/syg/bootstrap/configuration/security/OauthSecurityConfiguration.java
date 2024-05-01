
package syg.bootstrap.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración de Oauth
 */
@Configuration
@Profile("oauth")
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class OauthSecurityConfiguration {

    private static final String[] AUTH_WHITELIST = { "/v3/api-docs/**",
            "/swagger-ui.html", "/swagger-ui/**", "/openapi.json", "/error", "/actuator/prometheus" };
    
    @Value("${app.security.auth.certificates.url}")
    private String                authCerts;

    /**
     * Constructor OauthSecurityConfig
     */
    public OauthSecurityConfiguration() {
        super();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        return http.cors(Customizer.withDefaults())
            .authorizeHttpRequests(
                authz -> authz.requestMatchers(AUTH_WHITELIST)
                    .permitAll().anyRequest().authenticated())
            .oauth2ResourceServer(
                oauth2 -> oauth2.jwt(jwt -> jwt.jwkSetUri(authCerts)))
            .build();
    }

}