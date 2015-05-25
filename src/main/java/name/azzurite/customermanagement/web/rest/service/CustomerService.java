package name.azzurite.customermanagement.web.rest.service;

import name.azzurite.customermanagement.domain.entity.Customer;
import name.azzurite.customermanagement.domain.repository.CustomerRepository;
import name.azzurite.customermanagement.web.rest.error.Errors;
import name.azzurite.customermanagement.web.rest.error.ServiceException;
import name.azzurite.customermanagement.web.util.UniqueNameGenerator;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	private final Mapper mapper;

	@Inject
	public CustomerService(CustomerRepository customerRepository, Mapper mapper) {
		this.customerRepository = customerRepository;
		this.mapper = mapper;
	}

	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	public List<Customer> getAllOverview() {
		return customerRepository.findAllOverview();
	}

	public Optional<Customer> find(String uniqueName) {
		return Optional.ofNullable(customerRepository.findOne(uniqueName));
	}


	public Customer save(Customer customer) {
		if (!find(customer.getUniqueName()).isPresent()) {
			throw new ServiceException(HttpStatus.NOT_FOUND);
		}
		return customerRepository.save(customer);
	}

	public Customer create(Customer customer) {
		if (customer.getFirstName() == null || customer.getLastName() == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, Errors.create("CUSTOMER_WITHOUT_NAME"));
		}

		String customerName = customer.getFirstName() + " " + customer.getLastName();
		String uniqueName = UniqueNameGenerator.generate(customerName, this::find);
		customer.setUniqueName(uniqueName);
		return customerRepository.save(customer);
	}

	public void delete(String uniqueName) {
		customerRepository.delete(uniqueName);
	}
}
