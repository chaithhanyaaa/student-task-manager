package com.studenttaskmanager.repository;

import java.util.*;
import com.studenttaskmanager.model.*;

public interface TaskRepository 
{
	void save(Task task);
	List<Task> findAll();
	int deleteById(int id);


}
