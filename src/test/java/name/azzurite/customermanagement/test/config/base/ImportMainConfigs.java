package name.azzurite.customermanagement.test.config.base;

import name.azzurite.customermanagement.config.ConversionConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ConversionConfig.class})
public class ImportMainConfigs {
}
