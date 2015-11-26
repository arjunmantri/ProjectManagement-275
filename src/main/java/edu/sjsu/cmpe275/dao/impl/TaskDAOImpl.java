package edu.sjsu.cmpe275.dao.impl;

import edu.sjsu.cmpe275.dto.Task;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.dao.interfaces.ITaskDAO;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository("taskDAOImpl")
@Component

public class TaskDAOImpl implements ITaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void createTaskDAO(Task task) {
        sessionFactory.getCurrentSession().save(task);
    }

    public List<Task> GetTasksDAO() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
        return criteria.list();
    }
}