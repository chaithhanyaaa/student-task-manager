package com.studenttaskmanager.controller;

import com.studenttaskmanager.model.Task;
import com.studenttaskmanager.repository.MySqlTaskRepository;
import com.studenttaskmanager.repository.TaskRepository;
import com.studenttaskmanager.service.TaskService;
import com.studenttaskmanager.service.TaskServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/tasks")
public class TaskController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TaskService taskService;

    // 1️⃣ Servlet initialization
    @Override
    public void init() throws ServletException {
        TaskRepository repository = new MySqlTaskRepository();
        this.taskService = new TaskServiceImpl(repository);
    }

    // 2️⃣ CREATE TASK (POST /backend/tasks)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // 1. Read request body
        StringBuilder body = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        String json = body.toString();
        System.out.println("RAW JSON = " + json);

        // 2. Extract title SAFELY
        String title = null;

        if (json.contains("\"title\"")) {
            int start = json.indexOf("\"title\"") + 8;
            start = json.indexOf("\"", start) + 1;
            int end = json.indexOf("\"", start);
            title = json.substring(start, end);
        }

        System.out.println("PARSED TITLE = " + title);

        // 3. Validate
        if (title == null || title.isBlank()) {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"title is required\"}");
            return;
        }

        // 4. Save
        Task task = new Task(title, LocalDateTime.now());
        taskService.createTask(task);

        resp.setContentType("application/json");
        resp.getWriter().write("{\"message\":\"Task created\"}");
    }


    // 3️⃣ GET ALL TASKS (GET /backend/tasks)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException 
    {

        List<Task> tasks = taskService.getAllTasks();

        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            json.append("{")
                .append("\"id\":").append(t.getId()).append(",")
                .append("\"title\":\"").append(t.getTitle()).append("\",")
                .append("\"createdAt\":\"").append(t.getCreatedAt()).append("\"")
                .append("}");

            if (i < tasks.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.getWriter().write(json.toString());
    }
}
