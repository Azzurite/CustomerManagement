package name.azzurite.customermanagement.domain.entity;

public class ContactInfo {

	private String type;

	private String value;

	public ContactInfo() {
	}

	public ContactInfo(String type, String value) {
		this.type = type;
		this.value = value;
	}

	/**
	 * @return the type
	 */
	public String getType() { return type; }

	/**
	 * @param type the type to set
	 */
	public void setType(String type) { this.type = type; }

	/**
	 * @return the value
	 */
	public String getValue() { return value; }

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) { this.value = value; }
}
