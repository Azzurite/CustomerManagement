package name.azzurite.customermanagement.domain.entity;

import name.azzurite.customermanagement.domain.entity.component.UniqueName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Customer {

	public Customer() {
	}

	public Customer(UniqueName uniqueName) {
		this.uniqueName = uniqueName;
	}

	@Id
	private UniqueName uniqueName;
	public UniqueName getUniqueName() { return uniqueName; }
	public void setUniqueName(UniqueName uniqueName) { this.uniqueName = uniqueName; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Customer customer = (Customer) o;

		return uniqueName.equals(customer.uniqueName);

	}
	@Override
	public int hashCode() {
		return uniqueName.hashCode();
	}
}
