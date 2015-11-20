package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import dto.Project;


@Repository("projectDAOImpl")
public class ProjectDAOImpl implements IProjectDAO{

	@Autowired
	HibernateTemplate htemplate;
	
	@Override
	public void createProject(Project project) {
		// TODO Auto-generated method stub
		
		htemplate.save(project);
		
	}

}
