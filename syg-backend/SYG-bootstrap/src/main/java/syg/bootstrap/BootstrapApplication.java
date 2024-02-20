package syg.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "syg.**")
@EnableJpaRepositories("syg.mysql.repositories")
@EntityScan(basePackages = "syg.mysql.entities")
@PropertySource(value = "classpath:mysql.properties")
public class BootstrapApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BootstrapApplication.class, args);
	}
}
