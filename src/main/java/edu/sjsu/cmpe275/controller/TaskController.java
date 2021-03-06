package edu.sjsu.cmpe275.controller;

import edu.sjsu.cmpe275.dto.AssignedTaskDetails;
import edu.sjsu.cmpe275.dto.Task;
import edu.sjsu.cmpe275.service.impl.ProjectServiceImpl;
import edu.sjsu.cmpe275.service.impl.TaskServiceImpl;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * This class is meant for task management, 
 * @author Team - 3
 */
@RestController
@RequestMapping("/api/v1/*")
public class TaskController {

    @Autowired
    public TaskServiceImpl taskServiceImpl;
    
  //  @Autowired
  //  AssignedTaskDetails assignedTaskDetails;
    
    @Autowired
	public ProjectServiceImpl projectServiceImpl;

    @RequestMapping(method = RequestMethod.POST, value = "/{TaskTitle}/{TaskDescription}/{TaskAssignee}/{TaskState}/{TaskEstimate}/{TaskActual}/{ProjectId}/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void createTaskController(@PathVariable String TaskTitle,
                                         @PathVariable String TaskDescription,
                                         @PathVariable String TaskAssignee,
                                         @PathVariable String TaskState,
                                         @PathVariable int TaskEstimate,
                                         @PathVariable int TaskActual,
                                         @PathVariable long ProjectId) {
     taskServiceImpl.createTaskService(TaskTitle, TaskDescription, TaskAssignee, TaskState, TaskEstimate, TaskActual, ProjectId);
       
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{projectId}/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<Task> getTaskController(@PathVariable long projectId){
        return taskServiceImpl.getAllTaskService(projectId);
    }
    
    /*
     * Implementation for the delete task.
     */

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{TaskId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Task deleteTaskController(@PathVariable long TaskId){
        return taskServiceImpl.deleteTaskService(TaskId);
    }
    
    /*
     * Implementation for state change
     */

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/{TaskId}/{TaskState}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Task stateChangeTaskController(@PathVariable long TaskId,
                                          @PathVariable String TaskState){
        return taskServiceImpl.stateChangeTaskService(TaskId, TaskState);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/{TaskId}/{TaskTitle}/{TaskAssignee}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Task changeTaskAssigneeController(@PathVariable long TaskId,
                                             @PathVariable String TaskTitle,
                                             @PathVariable String TaskAssignee){
        return taskServiceImpl.changeAssigneeTaskService(TaskId, TaskTitle, TaskAssignee);
    }

    
    public List<Task> getAllTask(String emaildId) {
    	return taskServiceImpl.getAllTask(emaildId);
    }
    
    /*
     * Implemention to get all the task details for assigned user.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/allAssignedTask/{emailId}/", produces = {
            MediaType.APPLICATION_JSON_VALUE}) 
     public Map<String, AssignedTaskDetails> getAllTaskDetailsForAssignedUser(@PathVariable String emailId) {
    	List<Task> tasks = getAllTask(emailId);
    	List<AssignedTaskDetails> assignedTaskDetailsList = new ArrayList<AssignedTaskDetails>();
    	Map <String, AssignedTaskDetails> assignedhashMap = new HashMap<String, AssignedTaskDetails>();
    	AssignedTaskDetails assignedTaskDetails;
		
    	for(Task task : tasks) {
    		long projectId = task.getProjectId();
    		if(assignedhashMap.containsKey(String.valueOf(projectId))) {
    			AssignedTaskDetails assignedTask = assignedhashMap.get(String.valueOf(projectId));
    			List lst = assignedTask.getTasks();
    			System.out.println("-----size of the list-----"+lst.size());
    			lst.add(task);
    		} else {
    			List<String> projectDetaials= projectServiceImpl.getProjectDetailsForAssignedUser(projectId);
    			assignedTaskDetails = new AssignedTaskDetails();
    			assignedTaskDetails.setDescription(projectDetaials.get(0));
        		assignedTaskDetails.setProjectId(projectDetaials.get(1));
        		assignedTaskDetails.setTitle(projectDetaials.get(2));
        		List<Task> taskList = new ArrayList<Task>();
        		taskList.add(task);
        		assignedTaskDetails.setTasks(taskList);
        		assignedhashMap.put(String.valueOf(projectId), assignedTaskDetails);
    		}
      	}
    	return assignedhashMap;
    }
    
    /*
     * Get all the asignee for the task.
     */
    
    @RequestMapping(method = RequestMethod.GET, value = "/allAssignee/{projectId}/", produces = {
            MediaType.APPLICATION_JSON_VALUE}) 
    public List<String> getAllTaskAsignee(@PathVariable String projectId) {
    	System.out.println("---projectId-String---"+projectId);
    	long projId = Long.parseLong(projectId);
    	System.out.println("---projectId-long---"+projectId);
    	
    	return taskServiceImpl.getAllTaskAsignee(projId);
    }
    
}