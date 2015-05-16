package name.azzurite.customermanagement.domain.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.SortedMap;

public class History<T> {

	@Id
	private ObjectId id;

	@DBRef
	private Customer customer;

	private String key;

	private Class<T> valueClass;

	private SortedMap<LocalDateTime, T> history;

	/**
	 * @return the key
	 */
	public String getKey() { return key; }

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) { this.key = key; }

	/**
	 * @return the id
	 */
	public ObjectId getId() { return id; }

	/**
	 * @param id set the id
	 */
	public void setId(ObjectId id) { this.id = id; }

	/**
	 * @return the customer
	 */
	public Customer getCustomer() { return customer; }

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) { this.customer = customer; }

	/**
	 * @return the valueClass
	 */
	public Class<?> getValueClass() { return valueClass; }

	/**
	 * @param valueClass the valueClass to set
	 */
	public void setValueClass(Class<T> valueClass) { this.valueClass = valueClass; }

	/**
	 * @return the history
	 */
	public SortedMap<LocalDateTime, T> getHistory() { return history; }

	/**
	 * @param history the history to set
	 */
	public void setHistory(SortedMap<LocalDateTime, T> history) { this.history = history; }

}
