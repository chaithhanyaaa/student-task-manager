package com.studenttaskmanager.service;
import java.time.LocalDateTime;
import com.studenttaskmanager.model.*;


public class TaskServiceImpl implements TaskService {

    @Override
    public Task createTask(String title) {
       
        return new Task(title.toUpperCase(), LocalDateTime.now());
    }
}
