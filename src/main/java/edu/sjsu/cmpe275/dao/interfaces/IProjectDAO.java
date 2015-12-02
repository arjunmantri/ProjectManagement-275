package edu.sjsu.cmpe275.dao.interfaces;

import java.util.List;
import java.util.Set;

import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.dto.Task;


public interface IProjectDAO {
	Project createProject(Project project);
	Project getProjectById(long Id);
	List<Project> getAllProjectByEmailId(String emailId);
	void updateProject(Project project);
	List<Project> deleteProject(long id, String emailId);
	void updateByProjectId(long id, String state);
	Set<Task> getAllTaskProjectById(long id);
	List<String> getProjectDetailsForAssignedUser(long projectId);
}
