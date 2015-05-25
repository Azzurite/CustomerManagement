package name.azzurite.customermanagement.web.util;

//import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;

public class UniqueNameGenerator implements Serializable {

	private static int idx = 1;

	private UniqueNameGenerator() {
	}

	/**
	 * Generate a unique name for the given base string by using the find function to see if an entity with that unique
	 * name already exists. <br/> <br/> Removes reserved URL characters and spaces and converts the string to upper
	 * case.<br/> If the resulting string is found by the findFunction, adds a incrementing number at the end, until
	 * the
	 * find function doesn't find an entity anymore.
	 *
	 * @param base the base to use as a unique name
	 * @param findFunction the find function that returns an empty optional if the entity with the given unique name
	 * does not already exist
	 * @return the found unique name
	 */
	public static String generate(String base, Function<String, Optional<?>> findFunction) {
		String strippedUriReservedChars = base.replaceAll("[!\\*'\\(\\);:@&=+$,/\\?#\\[\\]%]", "");
		String spacesToUnderscore = strippedUriReservedChars.replace(' ', '_');
		String finalUniqueName = spacesToUnderscore.toUpperCase(Locale.GERMANY);

		if (!findFunction.apply(finalUniqueName).isPresent()) {
			return finalUniqueName;
		}

		int idx = 1;
		String curUniqueName = finalUniqueName + idx;
		while (findFunction.apply(curUniqueName).isPresent()) {
			++idx;
			curUniqueName = finalUniqueName + idx;
		}

		return curUniqueName;
	}

}
