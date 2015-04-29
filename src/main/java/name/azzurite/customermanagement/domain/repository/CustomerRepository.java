package name.azzurite.customermanagement.domain.repository;

import name.azzurite.customermanagement.domain.entity.Customer;
import name.azzurite.customermanagement.domain.entity.component.UniqueName;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends TypeSafeJPARepository<Customer, UniqueName> {

}
