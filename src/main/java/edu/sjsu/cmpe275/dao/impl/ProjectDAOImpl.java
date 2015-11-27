package edu.sjsu.cmpe275.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.dao.interfaces.IProjectDAO;
import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.dto.User;

@Repository
public class ProjectDAOImpl implements IProjectDAO {
	ProjectDAOImpl () {
		
	}
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Project createProject(Project project) {
		System.out.println("-----createProject--------");
		long id1 = (Long) sessionFactory.getCurrentSession().save(project);
		return getProjectById(id1);
	}
	
	@Override
	public Project getProjectById(long Id) {
		System.out.println("-----getUserDetail--------");
		return (Project) sessionFactory.getCurrentSession().get(Project.class, Id);
    }
	
	@Override
	public List<Project> getAllProjectByEmailId(String emailId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Project.class);
		criteria.add(Restrictions.eq("projectOwnerEmail", emailId));
		return  criteria.list();
	}
}

