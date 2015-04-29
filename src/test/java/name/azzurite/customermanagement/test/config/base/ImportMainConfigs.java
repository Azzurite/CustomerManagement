package name.azzurite.customermanagement.test.config.base;

import name.azzurite.customermanagement.config.DozerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DozerConfig.class})
public class ImportMainConfigs {
}
