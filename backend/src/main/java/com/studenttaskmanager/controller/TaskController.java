package com.studenttaskmanager.controller;
import com.studenttaskmanager.repository.MySqlTaskRepository;
import com.studenttaskmanager.repository.TaskRepository;
import com.studenttaskmanager.service.TaskService;
import com.studenttaskmanager.service.TaskServiceImpl;
import com.studenttaskmanager.model.Task;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


@WebServlet("/tasks")
public class TaskController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TaskService taskService;

    @Override
    public void init() throws jakarta.servlet.ServletException {
        TaskRepository repository = new MySqlTaskRepository();
        this.taskService = new TaskServiceImpl(repository);
    }
    
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest req,
                          jakarta.servlet.http.HttpServletResponse resp)
            throws jakarta.servlet.ServletException, java.io.IOException {

        String title = req.getParameter("title");

        Task task = new Task(title, java.time.LocalDateTime.now());
        taskService.createTask(task);

        resp.setContentType("text/plain");
        resp.getWriter().write("Task created");
    }
    
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req,
                         jakarta.servlet.http.HttpServletResponse resp)
            throws jakarta.servlet.ServletException, java.io.IOException {

        resp.getWriter().write("GET is working, use POST to create task");
    }


}
