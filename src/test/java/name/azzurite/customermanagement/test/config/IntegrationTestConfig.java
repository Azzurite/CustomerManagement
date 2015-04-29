package name.azzurite.customermanagement.test.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan({
		"name.azzurite.customermanagement.test.config.base",
		"name.azzurite.customermanagement.test.config.integration",
		"name.azzurite.customermanagement.web.rest"
})
@EntityScan("name.azzurite.customermanagement.domain.entity")
@EnableJpaRepositories("name.azzurite.customermanagement.domain.repository")
public class IntegrationTestConfig {

}
