package edu.sjsu.cmpe275.dao.interfaces;

import java.util.List;

import edu.sjsu.cmpe275.dto.Project;


public interface IProjectDAO {
	Project createProject(Project project);
	Project getProjectById(long Id);
	List<Project> getAllProjectByEmailId(String emailId);
}
