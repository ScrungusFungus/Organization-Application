package com.becca.registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;


public class ApplicationDao implements ApplicationService {

    public Map<UUID, Task> readTasks() {
        Task task = null;
        Map<UUID, Task> tasks = new LinkedHashMap<UUID, Task>();

        try {
            
        	Class.forName("com.mysql.cj.jdbc.Driver"); 
        	Connection con = null;
        	
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstone", "root", "root"); 

            String sql = "select * from tasks;";
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                task = new Task();
                task.setId(UUID.fromString(set.getString("id")));
                task.setTitle(set.getString("name"));
                task.setDate(set.getString("date"));
                task.setContent(set.getString("content"));
                tasks.put(task.getId(), task);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return tasks;
    }

    public Task readTask(String id) {
        Task task = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver"); 
        	Connection con = null;
        	
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstone", "root", "root"); 

            String sql = "select * from tasks where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                task = new Task();
                task.setId(UUID.fromString(set.getString("id")));
                task.setTitle(set.getString("name"));
                task.setDate(set.getString("date"));
                task.setContent(set.getString("content"));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return task;
    }

    public void createTask(Task Task) {
        try {

        	Class.forName("com.mysql.cj.jdbc.Driver"); 
        	Connection con = null;
        	
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstone", "root", "root"); 

            String sql = "insert into tasks (id, name, date, content) values (?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, Task.getId().toString());
            statement.setString(2, Task.getTitle());
            statement.setString(3, Task.getDate());
            statement.setString(4, Task.getContent());

            statement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void updateTask(Task Task) {
        try {

            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "update Tasks set title=?, content=? where uuid=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Task.getTitle());
            statement.setString(2, Task.getContent());
            statement.setString(3, Task.getId().toString());

            statement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void deleteTask(String id) {
        try {

        	Class.forName("com.mysql.cj.jdbc.Driver"); 
        	Connection con = null;
        	
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstone", "root", "root"); 

            String sql = "delete from tasks where id=?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, UUID.fromString(id).toString());

            statement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void createOrUpdateTask(Task Task) {
        Task localTask = readTask(Task.getId().toString());
        if (localTask == null) {
            createTask(Task);
        } else {
            updateTask(Task);
        }
    }

	@Override
	public void createUser(String name, String email, String pass, String contact) {
		Connection con = null;
		try { 

			Class.forName("com.mysql.cj.jdbc.Driver"); 
        	
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstone", "root", "root"); 
			PreparedStatement pst = con.prepareStatement("insert into users(uname,upwd,uemail,emobile) values(?,?,?,?)"); 
			pst.setString(1, name); 

			pst.setString(2, email); 

			pst.setString(3, pass); 

			pst.setString(4, contact); 


			}catch(Exception e) { 

			e.printStackTrace(); 

			}finally { 

			try { 

			con.close(); 

			} catch (SQLException e) { 

			// TODO Auto-generated catch block 

			e.printStackTrace(); 

			} 

			} 
		
	}

	@Override
	public void loginUser(String email, String pass) {
		// TODO Auto-generated method stub
		
	}

}
