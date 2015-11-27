package edu.sjsu.cmpe275.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.service.impl.ProjectServiceImpl;

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
			Project proj = projectServiceImpl.createPorject(title, description, state, projectOwnerEmail);
			return new ResponseEntity<Project>(proj, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/project/{emailId}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Project>> getAllProjectByEmailId(
			@PathVariable String emailId) {
		List<Project> proj = projectServiceImpl.getAllProjectByEmailId(emailId);
		return new ResponseEntity<List<Project>>(proj, HttpStatus.OK);
	}
}