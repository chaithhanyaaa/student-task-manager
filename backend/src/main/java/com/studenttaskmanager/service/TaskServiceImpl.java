package com.studenttaskmanager.service;

import java.util.*;

import com.studenttaskmanager.model.Task;
import com.studenttaskmanager.repository.TaskRepository;

public class TaskServiceImpl implements TaskService 
{
	private final TaskRepository repository;

	public TaskServiceImpl(TaskRepository repository)
	{
		this.repository=repository;
	}
	
	public void createTask(Task task)
	{
		repository.save(task);
	}
	
	public List<Task>  getAllTasks()
	{
		repository.findAll();
	}

}
