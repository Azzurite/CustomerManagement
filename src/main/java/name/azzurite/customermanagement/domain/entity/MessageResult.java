package name.azzurite.customermanagement.domain.entity;

import java.util.Optional;

public class MessageResult {


	private String text;

	private Optional<String> newTask;


	private Optional<String> newStatus;

	public MessageResult() {
	}


	/**
	 * @return the text
	 */
	public String getText() { return text; }

	/**
	 * @param text the text to set
	 */
	public void setText(String text) { this.text = text; }

	/**
	 * @return the newTask
	 */
	public Optional<String> getNewTask() { return newTask; }

	/**
	 * @param newTask the newTask to set
	 */
	public void setNewTask(Optional<String> newTask) { this.newTask = newTask; }

	/**
	 * @return the newStatus
	 */
	public Optional<String> getNewStatus() { return newStatus; }

	/**
	 * @param newStatus the newStatus to set
	 */
	public void setNewStatus(Optional<String> newStatus) { this.newStatus = newStatus; }

}
