package edu.sjsu.cmpe275.controller;

import edu.sjsu.cmpe275.dto.Task;
import edu.sjsu.cmpe275.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/tc/*")
public class TaskController {

    @Autowired
    public TaskServiceImpl taskServiceImpl;

    @RequestMapping(method = RequestMethod.POST, value = "/{TaskTitle}/{TaskDescription}/{TaskAssignee}/{TaskState}/{TaskEstimate}/{TaskActual}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    void createTaskController(@PathVariable String TaskTitle,
                              @PathVariable String TaskDescription,
                              @PathVariable String TaskAssignee,
                              @PathVariable String TaskState,
                              @PathVariable int TaskEstimate,
                              @PathVariable int TaskActual) {
        System.out.println("inside post request");
        taskServiceImpl.createTaskService(TaskTitle, TaskDescription, TaskAssignee, TaskState, TaskEstimate, TaskActual);
        //return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/Tasks",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    List<Task> getTaskController(){
               return taskServiceImpl.GetTaskService();
    }

}