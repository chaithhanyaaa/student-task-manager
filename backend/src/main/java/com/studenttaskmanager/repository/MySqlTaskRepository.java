package com.studenttaskmanager.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.studenttaskmanager.model.Task;

public class MySqlTaskRepository implements TaskRepository
{
	
	private static final String INSERT_SQL= "INSERT INTO tasks (title, created_at) VALUES (?, ?)";
	private static final String SELECT_ALL_SQL ="SELECT id, title, created_at FROM tasks";
	Connection conn =null;
	PreparedStatement ps=null;
	 public void save(Task task) 
	 {
	        
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
	    	public List<Task> findAll() 
	    	{

	    	    List<Task> tasks = new ArrayList<>();

	    	    Connection conn = null;
	    	    PreparedStatement ps = null;
	    	    ResultSet rs = null;

	    	    try {
	    	        conn = DbConnectionUtil.getConnection();
	    	        ps = conn.prepareStatement(SELECT_ALL_SQL);
	    	        rs = ps.executeQuery();

	    	        while (rs.next()) {
	    	            Task task = new Task(
	    	                rs.getInt("id"),
	    	                rs.getString("title"),
	    	                rs.getTimestamp("created_at").toLocalDateTime()
	    	            );
	    	            tasks.add(task);
	    	        }

	    	    } catch (SQLException e) {
	    	        throw new RuntimeException("Error fetching tasks", e);

	    	    } finally {
	    	        try {
	    	            if (rs != null) rs.close();
	    	            if (ps != null) ps.close();
	    	            if (conn != null) conn.close();
	    	        } catch (SQLException ignored) {
	    	        }
	    	    }

	    	    return tasks;
	    	}
	    	
	    	
	    	
	    	
	    	@Override
	    	public int deleteById(int id) {

	    	    String sql = "DELETE FROM tasks WHERE id = ?";

	    	    try (Connection connection = DbConnectionUtil.getConnection();
	    	         PreparedStatement stmt = connection.prepareStatement(sql)) {

	    	        stmt.setInt(1, id);
	    	        return stmt.executeUpdate(); // 👈 VERY IMPORTANT

	    	    } catch (Exception e) {
	    	        throw new RuntimeException(e);
	    	    }
	    	}


	    
	

}
