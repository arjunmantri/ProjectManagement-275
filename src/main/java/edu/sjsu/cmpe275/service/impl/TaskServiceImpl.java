package edu.sjsu.cmpe275.service.impl;

import config.HibernateConfig;
import edu.sjsu.cmpe275.dao.impl.TaskDAOImpl;
import edu.sjsu.cmpe275.dto.Task;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.service.interfaces.ITaskService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@ComponentScan(basePackages = {"edu.sjsu.cmpe275.*"})
@EnableTransactionManagement
//@Transactional
public class TaskServiceImpl implements ITaskService{

    @Autowired
    TaskDAOImpl taskDAOImpl;

    public void createTaskService(String TaskTitle, String TaskDescription, String TaskAssignee, String TaskState, int TaskEstimate, int TaskActual){
        Task task = new Task();
        task.setTitle(TaskTitle);
        task.setDescription(TaskDescription);
        task.setAssignee(TaskAssignee);
        task.setState(TaskState);
        task.setEstimate(TaskEstimate);
        task.setActual(TaskActual);
        taskDAOImpl.createTaskDAO(task);
    }

    public List<Task> GetTaskService(){
        return taskDAOImpl.GetTasksDAO();
    }

}
