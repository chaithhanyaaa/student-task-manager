

package com.studenttaskmanager.controller;

import com.studenttaskmanager.service.TaskService;
import com.studenttaskmanager.service.TaskServiceImpl;
import com.studenttaskmanager.model.Task;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tasks")
public class TaskController extends jakarta.servlet.http.HttpServlet {

    private TaskService taskService;

    @Override
    public void init() {
        this.taskService = new TaskServiceImpl();
    }

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp)
            throws IOException {

        // calling SERVICE (not creating Task here)
        Task task = taskService.createTask("learn backend");

        resp.setContentType("application/json");
        resp.getWriter().write(
            "{\"title\":\"" + task.getTitle() + "\"}"
        );
    }
}







