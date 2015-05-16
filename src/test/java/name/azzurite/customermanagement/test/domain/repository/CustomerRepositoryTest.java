package name.azzurite.customermanagement.test.domain.repository;

import name.azzurite.customermanagement.domain.entity.Customer;
import name.azzurite.customermanagement.domain.repository.CustomerRepository;
import name.azzurite.customermanagement.test.AbstractMongoIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CustomerRepositoryTest extends AbstractMongoIntegrationTest {

	@Inject
	private CustomerRepository customerRepository;

	@BeforeClass
	public static void db() {
		dbAddForTests(new Customer("TEST_CUSTOMER"));
	}

	@Test
	public void FindByUniqueName_ShouldReturnCustomer_IfCustomerExists() {
		Optional<Customer> customer = Optional.ofNullable(customerRepository.findOne("TEST_CUSTOMER"));
		assertThat(customer.isPresent(), is(true));

		Customer expectedCustomer = new Customer("TEST_CUSTOMER");
		assertThat(customer.get(), is(expectedCustomer));
	}

	@Test
	public void FindByUniqueName_ShouldReturnEmpty_IfCustomerIsMissing() {
		assertThat(customerRepository.findOne("TEST_CUSTOMER_2"), nullValue());
	}

	@Test
	public void CustomerExists_ShouldReturnFalse_IfCustomerIsMissing() {
		assertThat(customerRepository.exists("TEST_CUSTOMER_2"), is(false));
	}

	@Test
	public void CustomerExists_ShouldReturnTrue_IfCustomerExists() {
		assertThat(customerRepository.exists("TEST_CUSTOMER"), is(true));
	}


	@Test
	public void Save_ShouldSaveEntity() {
		Customer customer = new Customer("TEST_CUSTOMER_2");
		customerRepository.save(customer);
		assertThat(customerRepository.exists("TEST_CUSTOMER_2"), is(true));
	}

}
