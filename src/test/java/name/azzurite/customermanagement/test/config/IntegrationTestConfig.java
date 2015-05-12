package name.azzurite.customermanagement.test.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan({
		"name.azzurite.customermanagement.test.config.base",
		"name.azzurite.customermanagement.test.config.integration",
		"name.azzurite.customermanagement.web.rest"
})
@EnableMongoRepositories("name.azzurite.customermanagement.domain.repository")
public class IntegrationTestConfig {

}
