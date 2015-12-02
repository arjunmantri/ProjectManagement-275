package edu.sjsu.cmpe275.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

//import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import edu.sjsu.cmpe275.dao.interfaces.IProjectDAO;
import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.dto.ProjectStateCount;
import edu.sjsu.cmpe275.dto.Task;
import edu.sjsu.cmpe275.dto.User;


@Repository
public class ProjectDAOImpl implements IProjectDAO {
	ProjectDAOImpl () {
		
	}
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Project createProject(Project project) {
		long id1 = (Long) sessionFactory.getCurrentSession().save(project);
		return getProjectById(id1);
	}
	
	@Override
	public Project getProjectById(long Id) {
		return (Project) sessionFactory.getCurrentSession().get(Project.class, Id);
    }
	
	@Override
	public List<Project> getAllProjectByEmailId(String emailId) {
		System.out.println("--------------getAllProjectByEmailId------------>>"+emailId);
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Project.class);
		criteria.add(Restrictions.eq("projectOwnerEmail", emailId.trim()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  
		return  criteria.list();
	}
	
	@Override
	public void updateProject(Project project) {
		sessionFactory.getCurrentSession().update(project);
	}
	
	@Override
	public List<Project> deleteProject(long id, String emailId) {
		Project projectToBeDeleted = getProjectById(id);
		sessionFactory.getCurrentSession().delete(projectToBeDeleted);
		List<Project> allProjects = getAllProjectByEmailId(emailId);
		return allProjects;
	}
	
	@Override
	public void updateByProjectId(long id, String state) {
		Project project = getProjectById(id);
		project.setState(state);
		updateProject(project);
	}
	

	@Override
	public List<String> getProjectDetailsForAssignedUser (long id) {
		Project proj = (Project) sessionFactory.getCurrentSession().get(Project.class, id);
		List<String> details = new ArrayList<String>();
		details.add(proj.getDescription());
		details.add(String.valueOf(proj.getId()));
		details.add(proj.getTitle());
		return details;
	}
	
	@Override
	public Set<Task> getAllTaskProjectById(long projectId) {
		System.out.println("--------------getAllProjectByEmailId------------>>"+projectId);
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Project.class);
		criteria.add(Restrictions.eq("id", projectId));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  
		List<Project> proj =  criteria.list();
		return proj.get(0).getTasks();
		
	}
	
	@Override
	public ProjectStateCount getProjectStateCount(Long projectId) {
			String SQL = " SELECT  TaskState, Count(*) countTaskState  FROM  Tasks "
				+ " where ProjectId =" + projectId + " GROUP BY  TaskState";
		ProjectStateCount projectStateResult = new ProjectStateCount();
			
		List resultWithAliasedBeans = sessionFactory.getCurrentSession().createSQLQuery(SQL)
				  .addScalar("taskState", StringType.INSTANCE)
				  .addScalar("countTaskState",LongType.INSTANCE)
				  .setResultTransformer(new AliasToBeanResultTransformer(ProjectStateCount.class))
				  .list();
		projectStateResult.setProjectId(projectId);
		for( Object projState  : resultWithAliasedBeans) {
			ProjectStateCount projStateCount = (ProjectStateCount) projState;
			if(projStateCount.getTaskState().equalsIgnoreCase("new")) {
				projectStateResult.setCountOfNewState(projStateCount.getCountTaskState());
			}
			 else if(projStateCount.getTaskState().equalsIgnoreCase("assigned")) {
				 projectStateResult.setCountOfAssignedState(projStateCount.getCountTaskState());
			} else if(projStateCount.getTaskState().equalsIgnoreCase("started")) {
				projectStateResult.setCountOfStartedState(projStateCount.getCountTaskState());
			} else if(projStateCount.getTaskState().equalsIgnoreCase("finished")) {
				projectStateResult.setCountOfFinishedState(projStateCount.getCountTaskState());
			} else if(projStateCount.getTaskState().equalsIgnoreCase("cancelled")) {
				projectStateResult.setCountOfCancelledState(projStateCount.getCountTaskState());
			}
			
		}
		
		return projectStateResult;
}
}