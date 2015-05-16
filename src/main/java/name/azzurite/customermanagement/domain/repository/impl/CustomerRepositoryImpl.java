package name.azzurite.customermanagement.domain.repository.impl;

import name.azzurite.customermanagement.domain.entity.Customer;
import name.azzurite.customermanagement.domain.repository.CustomerRepositoryCustom;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

	@Inject
	private MongoOperations ops;

	@Override
	public List<Customer> findAllOverview() {
		Query q = new Query();

		q.fields()
				.include("firstName")
				.include("lastName")
				.include("type")
				.include("companies")
				.include("relationshipStatus")
				.include("currentTask");

		return ops.find(q, Customer.class);
	}
}
