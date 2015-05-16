package name.azzurite.customermanagement.domain.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class AutocompleteEntry {

	@Id
	private ObjectId id;

	private String key;

	private String value;

	/**
	 * @return the id
	 */
	public ObjectId getId() { return id; }

	/**
	 * @param id set the id
	 */
	public void setId(ObjectId id) { this.id = id; }

	/**
	 * @return the key
	 */
	public String getKey() { return key; }

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) { this.key = key; }

	/**
	 * @return the value
	 */
	public String getValue() { return value; }

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) { this.value = value; }

}
