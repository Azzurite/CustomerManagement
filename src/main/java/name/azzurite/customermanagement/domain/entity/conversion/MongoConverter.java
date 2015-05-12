package name.azzurite.customermanagement.domain.entity.conversion;

import org.springframework.core.convert.converter.Converter;

public interface MongoConverter<S, T> extends Converter<S, T> {
}
