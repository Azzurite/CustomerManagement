package name.azzurite.customermanagement.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Company {

	@Id
	private String uniqueName;

	private String name;

	private int workerCount;

	private String info;

	public Company(String uniqueName, String name, int workerCount, String info) {
		this.uniqueName = uniqueName;
		this.name = name;
		this.workerCount = workerCount;
		this.info = info;
	}

	public Company() {

	}

	public Company(String name, int workerCount, String info) {
		this(null, name, workerCount, info);
	}

	/**
	 * @return the uniqueName
	 */
	public String getString() { return uniqueName; }

	/**
	 * @param uniqueName the uniqueName to set
	 */
	public void setString(String uniqueName) { this.uniqueName = uniqueName; }

	/**
	 * @return the name
	 */
	public String getName() { return name; }

	/**
	 * @param name the name to set
	 */
	public void setName(String name) { this.name = name; }

	/**
	 * @return the workerCount
	 */
	public int getWorkerCount() { return workerCount; }

	/**
	 * @param workerCount the workerCount to set
	 */
	public void setWorkerCount(int workerCount) { this.workerCount = workerCount; }

	/**
	 * @return the info
	 */
	public String getInfo() { return info; }

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) { this.info = info; }


}
