package name.azzurite.customermanagement.domain.repository;

import name.azzurite.customermanagement.domain.entity.Customer;

import java.util.List;

public interface CustomerRepositoryCustom {

	List<Customer> findAllOverview();
}
