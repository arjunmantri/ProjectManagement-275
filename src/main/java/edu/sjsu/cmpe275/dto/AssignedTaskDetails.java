package edu.sjsu.cmpe275.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

public class AssignedTaskDetails {
	
	private String projectId;
	private String title;
	private String description;
	private List<Task> tasks ;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	
}
