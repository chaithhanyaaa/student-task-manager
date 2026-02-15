package com.studenttaskmanager.service;

import java.util.*;
import com.studenttaskmanager.model.Task;

public interface TaskService 
{

	 void createTask(Task task);

	 List<Task> getAllTasks();
	 
	 boolean deleteTaskById(int id);


}
