package name.azzurite.customermanagement.domain.entity.conversion;

import name.azzurite.customermanagement.domain.entity.component.UniqueName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class StringUniqueNameConversion {

	public static class UniqueNameToStringConverter implements MongoConverter<UniqueName, String> {

		@Override
		public String convert(UniqueName source) {
			return source.getUniqueName();
		}
	}

	public static class StringToUniqueNameConverter implements MongoConverter<String, UniqueName> {

		@Override
		public UniqueName convert(String source) {
			return new UniqueName(source);
		}
	}

	public static class StringUniqueNameDozerConverter extends AutoConfiguredDozerConverter<String, UniqueName> {

		public StringUniqueNameDozerConverter() {
			super(String.class, UniqueName.class);

		}

		@Override
		public UniqueName convertTo(String source, UniqueName destination) {
			return new StringToUniqueNameConverter().convert(source);
		}

		@Override
		public String convertFrom(UniqueName source, String destination) {
			return new UniqueNameToStringConverter().convert(source);
		}
	}

	@Bean
	public MongoConverter<String, UniqueName> stringToUniqueName() {
		return new StringToUniqueNameConverter();
	}

	@Bean
	public MongoConverter<UniqueName, String> uniqueNameToString() {
		return new UniqueNameToStringConverter();
	}


	@Bean
	public Converter<Integer, String> asd() {
		return new Converter<Integer, String>() {
			@Override
			public String convert(Integer source) {
				return "";
			}
		};
	}

	@Bean
	public AutoConfiguredDozerConverter stringUniqueNameDozerConverter() {
		return new StringUniqueNameDozerConverter();
	}
}
