package com.unlimited.oj.service.impl;

import java.util.List;

import com.unlimited.oj.dao.TaskDao;
import com.unlimited.oj.dao.support.Page;
import com.unlimited.oj.model.Task;
import com.unlimited.oj.service.TaskManager;

/**
 * Implementation of TaskManager interface.
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
public class TaskManagerImpl extends GenericManagerImpl<Task, Long> implements TaskManager {
    private TaskDao taskDao;

    public void setTaskDao(TaskDao dao) {
    	super.dao = dao;
        this.taskDao = dao;
    }
    public List<Task> getTasks(Task Task) {
        return taskDao.getAll();
    }
    public void saveTask(Task Task) {
        taskDao.save(Task);
    }
    public void removeTask(String Taskname) {
        taskDao.remove(Taskname);
    }
	public List getAllTasks() {
		// TODO Auto-generated method stub
		return taskDao.getAll();
	}
	public Task getTask(String taskname) {
		List list = taskDao.findBy("title", taskname);
		if(list!=null)
			return (Task)list.get(0);
		else 
			return null;
	}
}