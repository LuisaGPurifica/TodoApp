package learn.programming.entity;

import java.time.LocalDate;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Todo extends AbstractEntity{

	
	
	@NotEmpty(message = "Task must be set")
	@Size(min = 10, message = "Task should not be less than 10 characters")
	private String task;
	
	@NotEmpty(message = "Due date must be set")
	@FutureOrPresent(message = "Due date must be in the future or present")
	@JsonbDateFormat(value = "dd/mm/yyyy")
	private LocalDate dueDate;
	private boolean isCompleted;
	private LocalDate dateCompleted;
	private LocalDate dateCreated;
	
	@ManyToOne
	private User todoOwner;

	@PrePersist
	private void init() {
		setDateCreated(LocalDate.now());
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public LocalDate getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(LocalDate dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

}
