package name.azzurite.customermanagement.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


@Document
public class Customer {

	@Id
	private String uniqueName;

	private String firstName;

	private String lastName;

	private String type;

	private String niche;

	private List<Company> companies;

	private List<ContactInfo> contactInfo;

	private String relationshipStatus;

	private String currentTask;

	private Map<String, String> extraInfo;

	private List<Message> messages;

	public Customer() {}

	public Customer(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	/**
	 * @return the relationshipStatus
	 */
	public String getRelationshipStatus() { return relationshipStatus; }

	/**
	 * @param relationshipStatus the relationshipStatus to set
	 */
	public void setRelationshipStatus(String relationshipStatus) { this.relationshipStatus = relationshipStatus; }

	/**
	 * @return the currentTask
	 */
	public String getCurrentTask() { return currentTask; }

	/**
	 * @param currentTask the currentTask to set
	 */
	public void setCurrentTask(String currentTask) { this.currentTask = currentTask; }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	/**
	 * @return the extraInfo
	 */
	public Map<String, String> getExtraInfo() { return extraInfo; }

	/**
	 * @param extraInfo the extraInfo to set
	 */
	public void setExtraInfo(Map<String, String> extraInfo) { this.extraInfo = extraInfo; }

	/**
	 * @return the messages
	 */
	public List<Message> getMessages() { return messages; }

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<Message> messages) { this.messages = messages; }

	/**
	 * @return the niche
	 */
	public String getNiche() { return niche; }

	/**
	 * @param niche the niche to set
	 */
	public void setNiche(String niche) { this.niche = niche; }

	/**
	 * @return the companies
	 */
	public List<Company> getCompanies() { return companies; }

	/**
	 * @param companies the companies to set
	 */
	public void setCompanies(List<Company> companies) { this.companies = companies; }

	/**
	 * @return the contactInfo
	 */
	public List<ContactInfo> getContactInfo() { return contactInfo; }

	/**
	 * @param contactInfo the contactInfo to set
	 */
	public void setContactInfo(List<ContactInfo> contactInfo) { this.contactInfo = contactInfo; }

	/**
	 * @return the firstName
	 */
	public String getFirstName() { return firstName; }

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) { this.firstName = firstName; }

	/**
	 * @return the lastName
	 */
	public String getLastName() { return lastName; }

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) { this.lastName = lastName; }


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
