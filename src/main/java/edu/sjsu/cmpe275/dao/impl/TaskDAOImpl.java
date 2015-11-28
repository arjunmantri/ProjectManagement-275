package edu.sjsu.cmpe275.dao.impl;

import edu.sjsu.cmpe275.dto.Task;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.dao.interfaces.ITaskDAO;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
//@Repository("taskDAOImpl")
//@Component
//@Transactional
public class TaskDAOImpl implements ITaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //@Transactional
    public void createTaskDAO(Task task) {
        sessionFactory.getCurrentSession().save(task);
    }

//    only delete a task when projectId are same and task is in planning state.
//    public Task createTaskDAO(Task task) {
//        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
//        criteria.add(Restrictions.and(Restrictions.eq("ProjectId", ProjectId, Restrictions.eq("state", "planningState")));
//        sessionFactory.getCurrentSession().save(task);
//    }


    public List<Task> getAllTasksDAO() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
        return criteria.list();
    }

    public Task getTaskDAO(long TaskId){
        System.out.println("" + sessionFactory.getCurrentSession().getClass().getFields());
        return (Task) sessionFactory.getCurrentSession().get(Task.class, TaskId);
    }

    public Task deleteTaskDAO(long TaskId){
        Task task = getTaskDAO(TaskId);
        sessionFactory.getCurrentSession().delete(task);
        return task;
    }

    public Task stateChangeTaskDAO(Task task) {
        //Task task = getTaskDAO(TaskId);
        sessionFactory.getCurrentSession().merge(task);
        sessionFactory.getCurrentSession().update(task);
        return task;
    }
    
    public Task changeAssigneeTaskDAO(Task task) {
        sessionFactory.getCurrentSession().merge(task);
        sessionFactory.getCurrentSession().update(task);
        return task;
    }

//    only delete a task when projectId are same and task is in planning state.
//    public Task deleteTaskDAO1(long TaskId, long ProjectId) {
//        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
//        criteria.add(Restrictions.and(Restrictions.eq("ProjectId", ProjectId, Restrictions.eq("state", "planningState")));
//        List<Task> task =  criteria.list();
//        if(task.size() != 0) {
//            return task.get(0);
//        }
//        return null;
//    }

}