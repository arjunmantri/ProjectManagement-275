package edu.sjsu.cmpe275.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.service.impl.EmailServiceImpl;
import edu.sjsu.cmpe275.service.impl.ProjectServiceImpl;
import edu.sjsu.cmpe275.service.interfaces.IProjectService;

@RestController
@RequestMapping("/api/v1/*")
@ComponentScan(basePackages = {"edu.sjsu.cmpe275.service.impl"})
public class ProjectController {

	@Autowired
	public ProjectServiceImpl projectServiceImpl;
	
	@RequestMapping(method=RequestMethod.POST, value="/project/{title}/{description}/{state}/{projectOwnerEmail}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Project> createProject(
			@PathVariable String title, 
			@PathVariable String description,
			@PathVariable String state,
			@PathVariable String projectOwnerEmail) {
		System.out.println("----title----"+title);
		//Project proj = projectServiceImpl.createPorject(title, description, state, projectOwnerEmail);
		Project proj = projectServiceImpl.createPorject(title, description, state, projectOwnerEmail);
		System.out.println("------project---"+proj.getDescription());
		return new ResponseEntity<Project>(proj, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/project/{emailId}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Project>> getAllProjectByEmailId(
			@PathVariable String emailId) {
		System.out.println("----emailId----"+emailId);
		List<Project> proj = projectServiceImpl.getAllProjectByEmailId(emailId);
		System.out.println("------project---"+proj.get(0).getDescription());
		return new ResponseEntity<List<Project>>(proj, HttpStatus.OK);
	}
}



