package edu.sjsu.cmpe275.dto;
import org.springframework.stereotype.Service;

import java.io.Serializable;
//Follow pascal casing for the column names.
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Tasks")
@Service
public class Task implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="TaskId")
    @NotNull
    private long TaskId;

	@Column(name = "TaskTitle")
	@NotNull
	private String title;

	@Column(name = "TaskDescription")
	@NotNull
	private String description;

	@Column(name = "TaskAssignee")
	@NotNull
	private String assignee;

	@Column(name = "TaskState")
	@NotNull
	private String state;

	@Column(name = "TaskEstimate")
	@NotNull
	private int estimate;

	@Column(name = "TaskActual")
	@NotNull
	private int actual;
	
	@Column(name = "ProjectId")
	@NotNull
	private long projectId;

	public long getId() {
		return TaskId;
	}

	public void setId(long TaskId) {
		this.TaskId = TaskId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	public int getActual() {
		return actual;
	}

	public void setActual(int actual) {
		this.actual = actual;
	}
	
	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
}
