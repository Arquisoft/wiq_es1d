package syg.bootstrap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
	
	/**
	 * Obtiene el token del usuario actual para poder comunicarse con otros
	 * servicios REST que cuentan con autenticación. En caso de que no esté activa
	 * la autenticación, devuelve null.
	 * 
	 * @return token o null si la autenticación no está activada
	 */
	@Bean
	@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Jwt getAccessToken() {
		var servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		var request = servletRequestAttributes.getRequest();
		var userPrincipal = (JwtAuthenticationToken) request.getUserPrincipal();
		return userPrincipal != null ? userPrincipal.getToken() : null;
	}
	
}
