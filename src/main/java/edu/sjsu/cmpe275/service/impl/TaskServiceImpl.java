package edu.sjsu.cmpe275.service.impl;

import config.HibernateConfig;
import edu.sjsu.cmpe275.dao.impl.ProjectDAOImpl;
import edu.sjsu.cmpe275.dao.impl.TaskDAOImpl;
import edu.sjsu.cmpe275.dto.Project;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;









import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional
@ComponentScan(basePackages = {"edu.sjsu.cmpe275.*"})
//@Transactional(propagation = Propagation.SUPPORTS)
//public class TaskServiceImpl implements ITaskService{
public class TaskServiceImpl{

    @Autowired
    TaskDAOImpl taskDAOImpl;
    
    @Autowired
    ProjectDAOImpl projectDAOImpl;

    @Autowired
    Task task;

    public void createTaskService(String TaskTitle, String TaskDescription, String TaskAssignee, String TaskState, int TaskEstimate, int TaskActual, long ProjectId){
       // Task task = new Task();
    	Project project = projectDAOImpl.getProjectById(ProjectId);
    	task.setTitle(TaskTitle);
        task.setDescription(TaskDescription);
        task.setAssignee(TaskAssignee);
        task.setState(TaskState);
        task.setEstimate(TaskEstimate);
        task.setActual(TaskActual);
        task.setProjectId(ProjectId);
        
        Set <Task> taskList = project.getTasks();
        taskList.add(task);
        projectDAOImpl.updateProject(project);
        taskDAOImpl.createTaskDAO(task);

    }

    //@Transactional
    public Set<Task> getAllTaskService(long projectId) {
        Project project = projectDAOImpl.getProjectById(projectId);
        Set <Task> taskList = project.getTasks();
        return taskList;
    }


    public Task deleteTaskService(long TaskId){
        Task task = new Task();
        taskDAOImpl.deleteTaskDAO(TaskId);
        return task;
    }

    public Task stateChangeTaskService(long TaskId, String TaskState) {
        task = taskDAOImpl.getTaskDAO(TaskId);
        task.setState(TaskState);
        taskDAOImpl.stateChangeTaskDAO(task);
        return task;
    }

    public Task changeAssigneeTaskService(long TaskId, String TaskTitle, String TaskAssignee) {
        task = taskDAOImpl.getTaskDAO(TaskId);
        task.setAssignee(TaskAssignee);
        taskDAOImpl.changeAssigneeTaskDAO(task);
        return task;
    }
    
    public List<Task> getAllTask(String emailId) {
    	return taskDAOImpl.getAllTask(emailId);
    }
    
   public List<String> getAllTaskAsignee(long projectId) {
    	return taskDAOImpl.getAllTaskAsignee(projectId);
    }
//    public Task getTaskService(long TaskId){
//        return taskDAOImpl.getTaskDAO(TaskId);
//    }
//
//    public boolean doesTaskExistsService(long TaskId){
//        return
//    }
}
