package edu.sjsu.cmpe275.dao.interfaces;

import edu.sjsu.cmpe275.dto.Task;

import java.util.List;

public interface ITaskDAO {

    public void createTaskDAO(Task task);
    public List<Task> GetTasksDAO();
}
