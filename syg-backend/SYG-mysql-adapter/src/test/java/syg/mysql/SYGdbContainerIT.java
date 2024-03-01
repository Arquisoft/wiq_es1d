package syg.mysql;



import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

/**
 * Test que arranca un contenedor con una base de datos mySQL.
 *
 * Dado que los tests se ejecutan en un contexto transaccional no se afectan
 * unos a otros.
 * 
 * 
 * Los datos iniciales se cargan de una imagen.
 * 
 */
@Testcontainers
public abstract class SYGdbContainerIT {

	static final MySQLContainer<?> sygdbContainer;

	static {
		sygdbContainer = new MySQLContainer<>(
				DockerImageName.parse("mysql:8.0.36").asCompatibleSubstituteFor("mysql"))
				.withDatabaseName("syg-db")
				.withUsername("sygAdmin")
				.withPassword("sygAdmin")
				.withInitScript("db/initial-data.sql");
		sygdbContainer.start();
	}

	/**
	 * Asignamos las propiedades del DataSource de Spring utilizando las del
	 * contenedor
	 */
	@DynamicPropertySource
	static void mysqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", sygdbContainer::getJdbcUrl);
		registry.add("spring.datasource.password", sygdbContainer::getPassword);
		registry.add("spring.datasource.username", sygdbContainer::getUsername);
		registry.add("spring.datasource.driver-class-name", sygdbContainer::getDriverClassName);
	}

}
