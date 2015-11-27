package edu.sjsu.cmpe275.dao.interfaces;

import edu.sjsu.cmpe275.dto.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
public interface ITaskDAO {

    public void createTaskDAO(Task task);
    public List<Task> getAllTasksDAO();
    public Task getTaskDAO(long TaskTitle);
    public Task deleteTaskDAO(long TaskTitle);
    public void stateChangeTaskDAO();
}
