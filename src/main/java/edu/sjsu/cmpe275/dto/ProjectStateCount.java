package edu.sjsu.cmpe275.dto;

public class ProjectStateCount {
	
	public String taskState;
	public long countTaskState;
	
	public long ProjectId;
	public long countOfNewState;
	public long countOfAssignedState;
	public long countOfStartedState;
	public long countOfFinishedState;
	public long countOfCancelledState;
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	public long getCountTaskState() {
		return countTaskState;
	}
	public void setCountTaskState(long countTaskState) {
		this.countTaskState = countTaskState;
	}
	public long getProjectId() {
		return ProjectId;
	}
	public void setProjectId(long projectId) {
		ProjectId = projectId;
	}
	public long getCountOfNewState() {
		return countOfNewState;
	}
	public void setCountOfNewState(long countOfNewState) {
		this.countOfNewState = countOfNewState;
	}
	public long getCountOfAssignedState() {
		return countOfAssignedState;
	}
	public void setCountOfAssignedState(long countOfAssignedState) {
		this.countOfAssignedState = countOfAssignedState;
	}
	public long getCountOfStartedState() {
		return countOfStartedState;
	}
	public void setCountOfStartedState(long countOfStartedState) {
		this.countOfStartedState = countOfStartedState;
	}
	public long getCountOfFinishedState() {
		return countOfFinishedState;
	}
	public void setCountOfFinishedState(long countOfFinishedState) {
		this.countOfFinishedState = countOfFinishedState;
	}
	public long getCountOfCancelledState() {
		return countOfCancelledState;
	}
	public void setCountOfCancelledState(long countOfCancelledState) {
		this.countOfCancelledState = countOfCancelledState;
	}



	

}
