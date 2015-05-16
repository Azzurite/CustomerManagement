package name.azzurite.customermanagement.domain.entity;

import java.time.LocalDateTime;
import java.util.Optional;

public class Message {


	private LocalDateTime time;

	private String title;

	private String content;

	private Optional<String> assignedTask;

	private MessageResult result;

	public Message() {
	}

	public Message(LocalDateTime time, String title, String content,
				   Optional<String> assignedTask, MessageResult result) {

		this.time = time;
		this.title = title;
		this.content = content;
		this.assignedTask = assignedTask;
		this.result = result;
	}


	/**
	 * @return the time
	 */
	public LocalDateTime getTime() { return time; }

	/**
	 * @param time the time to set
	 */
	public void setTime(LocalDateTime time) { this.time = time; }

	/**
	 * @return the assignedTask
	 */
	public Optional<String> getAssignedTask() { return assignedTask; }

	/**
	 * @param assignedTask the assignedTask to set
	 */
	public void setAssignedTask(Optional<String> assignedTask) { this.assignedTask = assignedTask; }

	/**
	 * @return the title
	 */
	public String getTitle() { return title; }

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) { this.title = title; }

	/**
	 * @return the result
	 */
	public MessageResult getResult() { return result; }

	/**
	 * @param result the result to set
	 */
	public void setResult(MessageResult result) { this.result = result; }

	/**
	 * @return the content
	 */
	public String getContent() { return content; }

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) { this.content = content; }

}
