package name.azzurite.customermanagement.web.rest.resource;

import name.azzurite.customermanagement.domain.entity.Customer;
import name.azzurite.customermanagement.web.rest.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/rest/customers")
public class CustomerResource {

//	private static final Logger log = LogManager.getLogger(CustomerResource.class);

	private final CustomerService customerService;


	@Inject
	public CustomerResource(CustomerService customerService) {
		this.customerService = customerService;

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Customer customer) {
		HttpStatus statusCode;
		if (customerService.find(customer.getUniqueName()).isPresent()) {
			statusCode = HttpStatus.OK;
		} else {
			statusCode = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(customerService.save(customer), statusCode);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllOverview() {
		return new ResponseEntity<>(customerService.getAllOverview(), HttpStatus.OK);
	}


	@RequestMapping(value = "/{uniqueName}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable String uniqueName) {
		customerService.delete(uniqueName);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
