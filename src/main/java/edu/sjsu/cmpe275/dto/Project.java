package edu.sjsu.cmpe275.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PROJECT")
@Service
public class Project implements Serializable {

	private static final long serialVersionUID = -3239599941765020467L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name="TITLE") 
	@NotNull
	private String title;
	
	@Column(name="DESCRIPTION") 
	@NotNull
	private String description;
	
	@Column(name="STATE") 
	@NotNull
	private String state = "Planning";
	
	@Column(name="PROJECT_OWNER_EMAIL") 
	@NotNull
	private String projectOwnerEmail;
	
	@OneToMany (fetch = FetchType.EAGER)
	private Set<Task> tasks  = new HashSet<Task>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProjectOwnerEmail() {
		return projectOwnerEmail;
	}

	public void setProjectOwnerEmail(String projectOwnerEmail) {
		this.projectOwnerEmail = projectOwnerEmail;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}
