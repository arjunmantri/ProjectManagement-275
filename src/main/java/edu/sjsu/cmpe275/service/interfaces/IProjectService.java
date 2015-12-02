package edu.sjsu.cmpe275.service.interfaces;

import java.util.List;

import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.dto.ProjectStateCount;

public interface IProjectService {
	
	Project createPorject(Project project);
	
	Project getAllProjectByEmailId(String emailId);
	
	// Only the owner can cancel the project State should be in new, planning, completed state
	Project cancelProject(Project project);
	
	// Only owner can move the project to completed state 
	// If every taks is in finished state.
	// If every taks is in cancelled state.
	// One task is in finished state and all others are in cancelled state.
	// Once the task is in finished or in completed state it cannot be changed any more.
	Project stateUpdateOfProject(Project project);
	
	List<Project> deleteProject(long id, String emailId);
	
	ProjectStateCount getProjectStateCount(Long projectId);

}
