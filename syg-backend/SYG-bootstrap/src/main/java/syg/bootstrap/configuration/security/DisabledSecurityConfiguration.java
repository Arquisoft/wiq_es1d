
package syg.bootstrap.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase para deshabilitar la seguridad
 */
@Configuration
@Profile("unsecured")
public class DisabledSecurityConfiguration {

    /**
     * Constructor DisabledSecurityConfig
     */
    public DisabledSecurityConfiguration() {
        super();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return (web) -> web.ignoring()
            .requestMatchers("/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(registry -> registry.anyRequest()
                .permitAll())
            .build();
    }

}
