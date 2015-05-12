package name.azzurite.customermanagement.domain.repository;

import name.azzurite.customermanagement.domain.entity.Customer;
import name.azzurite.customermanagement.domain.entity.component.UniqueName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, UniqueName> {

}
