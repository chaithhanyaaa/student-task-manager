package com.studenttaskmanager.service;

import com.studenttaskmanager.model.Task;
import com.studenttaskmanager.repository.TaskRepository;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    // 👇 constructor injection (VERY IMPORTANT)
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createTask(Task task) {
        repository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }
    
    @Override
    public boolean deleteTaskById(int id) {
        int rowseffected=repository.deleteById(id);
        return rowseffected>0;
    }

}
