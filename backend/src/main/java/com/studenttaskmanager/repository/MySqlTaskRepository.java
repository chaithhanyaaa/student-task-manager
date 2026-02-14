package com.studenttaskmanager.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import com.studenttaskmanager.model.Task;

public class MySqlTaskRepository implements TaskRepository
{
	
	private static final String INSERT_SQL= "INSERT INTO tasks (title, created_at) VALUES (?, ?)";
	private static final String SELECT_ALL_SQL ="SELECT id, title, created_at FROM tasks";
	Connection conn =null;
	PreparedStatement ps=null;
	 public void save(Task task) {
	        
		 	try {
		 		conn= DbConnectionUtil.getConnection();
		 		ps = conn.prepareStatement(INSERT_SQL);
		 		ps.setString(1, task.getTitle());
		 	    ps.setTimestamp(2, java.sql.Timestamp.valueOf(task.getCreatedAt()));
		 	    ps.executeUpdate();
		 		
		 		
		 	}
		 	catch(Exception e)
		 	{
		 		throw new RuntimeException("Error saving task", e);
		 		
		 	}
		 	finally {

		 		try {
		 			if(ps!=null)
		 			{
		 				ps.close();
		 			}	
				}
		 		catch(Exception ignored)
		 		{}
		 		 try {
		             if (conn != null) conn.close();
		         } catch (Exception ignored) {}
				
			}
	        System.out.println("Saving task to MySQL: " + task.getTitle());
	    }

	    @Override
	    public List<Task> findAll() {
	        // JDBC code will come here
	        return new ArrayList<>();
	    }
	

}
