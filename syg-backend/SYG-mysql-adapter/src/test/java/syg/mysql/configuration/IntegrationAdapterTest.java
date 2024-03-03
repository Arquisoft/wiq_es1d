package syg.mysql.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import jakarta.transaction.Transactional;

@ContextConfiguration(classes = IntegrationAdapterTestConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Transactional
public @interface IntegrationAdapterTest {

}
