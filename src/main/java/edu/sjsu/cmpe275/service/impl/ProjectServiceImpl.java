package edu.sjsu.cmpe275.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.impl.ProjectDAOImpl;
import edu.sjsu.cmpe275.dao.interfaces.IProjectDAO;
import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.dto.ProjectStateCount;
import edu.sjsu.cmpe275.service.interfaces.IProjectService;


@Service
@ComponentScan(basePackages = {"edu.sjsu.cmpe275.*"})
@Transactional
public class ProjectServiceImpl  {
	
	ProjectServiceImpl() {
		
	}
	
	@Autowired 
	public ProjectDAOImpl projectDao;
	
	@Autowired
	public Project project;
	
	public Project createPorject(String title, String description, String state, String projectOwnerEmail) {
		project.setDescription(description);
		project.setState(state);
		project.setTitle(title);
		project.setProjectOwnerEmail(projectOwnerEmail);
		return projectDao.createProject(project);
	}
	
	public List<Project> getAllProjectByEmailId(String emailId) {
		return projectDao.getAllProjectByEmailId(emailId);
	}
	
	public void updateProject(Project project) {
		projectDao.updateProject(project);
	}
	
	public List<Project> deleteProject(long id, String emailId) {
		return projectDao.deleteProject(id, emailId);
	}
	
	public void updateByProjectId(long id, String state) {
		projectDao.updateByProjectId(id, state);
	}
	
	public List<String> getProjectDetailsForAssignedUser (long id) {
		return projectDao.getProjectDetailsForAssignedUser(id);
	}

	public ProjectStateCount getProjectStateCount(long projectId) {
		return projectDao.getProjectStateCount(projectId);
	}
}
