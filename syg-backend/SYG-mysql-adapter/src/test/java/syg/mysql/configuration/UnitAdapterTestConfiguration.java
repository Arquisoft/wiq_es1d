
package syg.mysql.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración que prepara el contexto de Spring que utilizará en los
 * tests unitarios.
 * 
 * Dado que este módulo no tiene una clase SpringBootApplication de por sí, (la
 * clase de arranque estará en el módulo anemo-backend-bootstrap)
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "syg.mysql.mapper", "syg.mysql.adapter" }, lazyInit = true)
public class UnitAdapterTestConfiguration {
}
