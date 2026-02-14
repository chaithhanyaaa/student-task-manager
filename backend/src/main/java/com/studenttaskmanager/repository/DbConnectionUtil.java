package com.studenttaskmanager.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3307/student_tasks";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
