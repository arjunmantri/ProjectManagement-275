package edu.sjsu.cmpe275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.service.interfaces.IProjectService;

@RestController
@RequestMapping("/api/pc/*")
public class ProjectController {
	
	/*
	@Autowired
	IProjectService projServ;
	
	@RequestMapping(value="/project", method=RequestMethod.POST)
	public ResponseEntity<Project> createProject(@RequestBody Project project){
		Project proj = projServ.createPorject(project);
		return new ResponseEntity<Project>(proj, HttpStatus.OK);
	}*/

}
