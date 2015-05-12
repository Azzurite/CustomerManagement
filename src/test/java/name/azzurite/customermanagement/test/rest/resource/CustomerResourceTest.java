package name.azzurite.customermanagement.test.rest.resource;

import name.azzurite.customermanagement.config.constants.WebConstants;
import name.azzurite.customermanagement.domain.entity.Customer;
import name.azzurite.customermanagement.domain.entity.component.UniqueName;
import name.azzurite.customermanagement.test.AbstractMongoIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerResourceTest extends AbstractMongoIntegrationTest {

	@BeforeClass
	public static void db() {
		dbAddForTests(new Customer(new UniqueName("TEST_CUSTOMER")));
	}

	@Test
	public void GetCustomers_ShouldReturnAllCustomers() throws Exception {
		mockMvc.perform(get("/rest/customers"))
			   .andExpect(jsonPath("[0].uniqueName", is("TEST_CUSTOMER")));
	}

	@Test
	public void PostCustomers_ShouldSaveCustomer() throws Exception {
		mockMvc.perform(post("/rest/customers").contentType(WebConstants.MEDIA_TYPE_JSON)
											   .content("{\"uniqueName\": \"TEST_CUSTOMER_2\"}"))
			   .andExpect(jsonPath("$.uniqueName", is("TEST_CUSTOMER_2")));

		mockMvc.perform(get("/rest/customers"))
			   .andExpect(jsonPath(".", hasSize(2)))
			   .andExpect(jsonPath("[1].uniqueName", is("TEST_CUSTOMER_2")));
	}

}
