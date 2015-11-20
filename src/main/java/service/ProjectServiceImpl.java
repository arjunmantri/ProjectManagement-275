package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dao.IProjectDAO;
import dto.Project;


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
