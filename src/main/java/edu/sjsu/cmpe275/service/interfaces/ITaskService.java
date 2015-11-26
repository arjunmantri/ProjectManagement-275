package edu.sjsu.cmpe275.service.interfaces;

import edu.sjsu.cmpe275.dto.Task;

import java.util.List;

public interface ITaskService {
    public void createTaskService(String TaskTitle, String TaskDescription, String TaskAssignee, String TaskState, int TaskEstimate, int TaskActual);
    public List<Task> GetTaskService();

}
