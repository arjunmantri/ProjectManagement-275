package edu.sjsu.cmpe275.controller;

import java.util.List;
import java.util.Set;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.dto.ProjectStateCount;
import edu.sjsu.cmpe275.dto.Task;
import edu.sjsu.cmpe275.service.impl.ProjectServiceImpl;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/*")
@ComponentScan(basePackages = {"edu.sjsu.cmpe275.service.impl"})
public class ProjectController {

	@Autowired
	public ProjectServiceImpl projectServiceImpl;
	
	@RequestMapping(method=RequestMethod.POST, value="/project/{title}/{description}/{state}/{projectOwnerEmail}/",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Project>> createProject(
			@PathVariable String title, 
			@PathVariable String description,
			@PathVariable String state,
			@PathVariable String projectOwnerEmail) {
			System.out.println("----------Email Id User Entered--->>"+projectOwnerEmail);
			Project proj = projectServiceImpl.createPorject(title, description, state, projectOwnerEmail);
			List<Project> allProject = projectServiceImpl.getAllProjectByEmailId(projectOwnerEmail);
			return new ResponseEntity<List<Project>>(allProject, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/project/{emailId}/",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Project>> getAllProjectByEmailId(
			@PathVariable String emailId) {
		System.out.println("--------emailId--GetRequest---"+emailId);
		List<Project> proj = projectServiceImpl.getAllProjectByEmailId(emailId);
		for(Project project : proj) {
			Set<Task> tasks= project.getTasks();
			for(Task taskone : tasks) {
				System.out.println(taskone.getActual());
				System.out.println(taskone.getDescription());
				System.out.println(taskone.getEstimate());
			}
		
		}
		return new ResponseEntity<List<Project>>(proj, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/project/{Id}/{emailId}/",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Project>> deleteProjectById(
			@PathVariable String Id,
			@PathVariable String emailId) {
		long idLong = Long.parseLong(Id);
		List<Project> proj =  projectServiceImpl.deleteProject(idLong, emailId);
		return new ResponseEntity<List<Project>>(proj, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/project/{id}/{state}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public void  updateByProjectId(
			@PathVariable String id,
			@PathVariable String state) {
		System.out.println("-----------Calling Project Update----");
		long idLong = Long.parseLong(id);
		projectServiceImpl.updateByProjectId(idLong, state);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/project/taskstatus/{projectId}/",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ProjectStateCount> getProjectStateCount(@PathVariable String projectId) {
		long idLong = Long.parseLong(projectId);
		ProjectStateCount projectStateCount = projectServiceImpl.getProjectStateCount(idLong);
		return new ResponseEntity<ProjectStateCount>(projectStateCount, HttpStatus.OK);
	}
}