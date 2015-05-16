package name.azzurite.customermanagement.test.rest.service;

import com.google.common.collect.Lists;
import name.azzurite.customermanagement.domain.entity.Customer;
import name.azzurite.customermanagement.domain.repository.CustomerRepository;
import name.azzurite.customermanagement.web.rest.service.CustomerService;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CustomerService.class, CustomerServiceTest.Config.class})
public class CustomerServiceTest extends AbstractServiceTest {

	@Inject
	private CustomerRepository mockRepository;

	@Inject
	private CustomerService customerService;

	@Inject
	private Mapper mapper;

	@Test
	public void GetAll_ShouldReturnListOfAllCustomers() {
		when(mockRepository.findAll())
				.thenReturn(Lists.newArrayList(new Customer("testCustomer")));

		List<Customer> all = customerService.getAll();
		Assert.assertThat(all, hasSize(1));
		Assert.assertThat(all, hasItem(samePropertyValuesAs(new Customer("testCustomer"))));
	}

	@Test
	public void GetOverview_ShouldReturnAllCustomers_WhenCalledWithNoPageParams() {
		fail();
	}

	@Test
	public void GetOverview_ShouldReturnFilteredCustomers_WhenCalledWithFilterParam() {
		Assert.fail();

	}

	@Test
	public void GetDetail_ShouldReturnCustomerDetail() {
		Assert.fail();

	}


	@Configuration
	public static class Config {

		@Bean
		public CustomerRepository customerRepository() {
			CustomerRepository mock = Mockito.mock(CustomerRepository.class);
			return mock;
		}
	}
}
