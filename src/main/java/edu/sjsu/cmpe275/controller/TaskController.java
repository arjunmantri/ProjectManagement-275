package edu.sjsu.cmpe275.controller;

import edu.sjsu.cmpe275.dto.Task;
import edu.sjsu.cmpe275.service.impl.TaskServiceImpl;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/tc/*")
public class TaskController {

    @Autowired
    public TaskServiceImpl taskServiceImpl;

    @RequestMapping(method = RequestMethod.POST, value = "/{TaskTitle}/{TaskDescription}/{TaskAssignee}/{TaskState}/{TaskEstimate}/{TaskActual}/{ProjectId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response createTaskController(@PathVariable String TaskTitle,
                                         @PathVariable String TaskDescription,
                                         @PathVariable String TaskAssignee,
                                         @PathVariable String TaskState,
                                         @PathVariable int TaskEstimate,
                                         @PathVariable int TaskActual,
                                         @PathVariable long ProjectId) {
        System.out.println("inside post request");
        taskServiceImpl.createTaskService(TaskTitle, TaskDescription, TaskAssignee, TaskState, TaskEstimate, TaskActual, ProjectId);
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/tasks",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Task> getTaskController(){
        return taskServiceImpl.getAllTaskService();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{TaskId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Task deleteTaskController(@PathVariable long TaskId){
        return taskServiceImpl.deleteTaskService(TaskId);
    }


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




}