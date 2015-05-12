package name.azzurite.customermanagement;

import name.azzurite.customermanagement.config.constants.BuildConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.SimpleCommandLinePropertySource;

/**
 * Main application class
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan
public class CustomerManagement {

	private static final Logger log = LogManager.getLogger(CustomerManagement.class);

	private static final String SPRING_PROFILE_ARG = "spring.profiles.active";

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CustomerManagement.class);

		if (!isProfileSet(args)) {
			addDevProfile(app);
		}

		app.run(args);
	}

	private static void addDevProfile(SpringApplication app) {
		log.warn("Using default profile: {}", BuildConstants.SPRING_PROFILE_DEV);
		app.setAdditionalProfiles(BuildConstants.SPRING_PROFILE_DEV);
	}

	private static boolean isProfileSet(String[] args) {
		SimpleCommandLinePropertySource cmdLine = new SimpleCommandLinePropertySource(args);
		return cmdLine.containsProperty(SPRING_PROFILE_ARG);
	}

}
