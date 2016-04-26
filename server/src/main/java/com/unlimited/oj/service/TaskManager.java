package com.unlimited.oj.service;

import com.unlimited.oj.model.Task;

import java.util.List;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface TaskManager extends GenericManager<Task, Long> {
    /**
     * {TaskDao}
     */
    List getAllTasks();
    Task getTask(String taskname);
    void saveTask(Task task);
    void removeTask(String taskName);
}
