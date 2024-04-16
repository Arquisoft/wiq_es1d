package syg.mysql.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase de configuración que prepara el contexto de Spring que utilizará en los
 * tests de integración.
 * 
 * Dado que este módulo no tiene una clase SpringBootApplication de por sí, (la
 * clase de arranque estará en el módulo anemo-backend-bootstrap)
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"syg.mysql.**", "syg.domain.**"})
@EnableJpaRepositories(basePackages = "syg.mysql.**.repositories")
@EntityScan(basePackages = "syg.mysql.**.entities")
public class IntegrationAdapterTestConfiguration {
}

