package name.azzurite.customermanagement.config;

import name.azzurite.customermanagement.domain.entity.conversion.AutoConfiguredDozerConverter;
import name.azzurite.customermanagement.domain.entity.conversion.MongoConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.classmap.MappingFileData;
import org.dozer.loader.DozerBuilder;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.util.List;

@Configuration
@ComponentScan("name.azzurite.customermanagement.domain.entity.conversion")
public class ConversionConfig {


	@Bean
	public CustomConversions customConversions(List<MongoConverter> converters) {
		return new CustomConversions(converters);
	}

	/**
	 * Abuses the BeanMappingBuilder to build the {@link org.dozer.classmap.Configuration} object programatically
	 * instead of via XML
	 */
	@Bean
	public BeanMappingBuilder buildConfiguration(List<AutoConfiguredDozerConverter> customConverters) {
		// since Dozer doesn't explicitly support programatically setting the Configuration, we have to abuse the
		// BeanMappingBuilder class a bit. In the end, this doesn't build
		// bean mappings anymore and instead builds the configuration.
		return new BeanMappingBuilder() {
			// normally this would be hidden and managed by the BeanMappingBuilder class
			private DozerBuilder dozerBuilder = new DozerBuilder();

			@Override
			protected void configure() {
				DozerBuilder.ConfigurationBuilder configuration = dozerBuilder.configuration();
				customConverters.stream().forEach((converter) -> {
					configuration.customConverter(converter.getClass())
								 .classA(converter.getClassA())
								 .classB(converter.getClassB());
				});
			}

			@Override
			public MappingFileData build() {
				// this is exactly the same as the overridden implementation, except that it uses another DozerBuilder
				configure();
				return dozerBuilder.build();
			}
		};
	}

	@Bean
	public DozerBeanMapper mapper(BeanMappingBuilder mappingBuilder) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(mappingBuilder);
		return mapper;
	}
}
