package name.azzurite.customermanagement.test.rest.error;

import name.azzurite.customermanagement.web.rest.error.Error;
import name.azzurite.customermanagement.web.rest.error.Errors;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ErrorsTest {

	@Test
	public void Create_ShouldBuildErrorObject() {
		Error error = Errors.createWithData("test.error").addData("works", true).get();

		assertThat(error.getErrorKey(), is("test.error"));
		assertThat(error.getAdditionalData(), hasEntry("works", true));
	}
}
