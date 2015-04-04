package name.azzurite.CustomerManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main application class
 */
@RestController
@EnableAutoConfiguration
public class CustomerManagement {

	@RequestMapping("/")
	public String test() {
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagement.class, args);
	}
}
