
package configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para los tests de controlador
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({ "syg.controller.**" })
public class ControllerTestsConfiguration {

}
