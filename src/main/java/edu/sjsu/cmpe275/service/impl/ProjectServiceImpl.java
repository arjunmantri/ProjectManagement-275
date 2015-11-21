package edu.sjsu.cmpe275.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.dao.interfaces.IProjectDAO;
import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.service.interfaces.IProjectService;


@Component
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	IProjectDAO projDao;
	
	@Override
	public Project createPorject(Project project) {
		// TODO Auto-generated method stub
		
		
		return project;
	}

	
	
}
