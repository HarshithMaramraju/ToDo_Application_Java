package com.ToDo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.Persistence;

import com.mysql.cj.protocol.Resultset;

public class ToDoDao {

	public ToDoDao(Connection connection) {
		super();
		this.connection = connection;
	}

	public static Connection connection;
	
	public static Connection gettconn() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo_app","root","root");
		} catch (Exception e) {
			// TODO: handle exception
		}
	return connection;
	}
	
	public boolean addToDo(String name, String todo, String status) {
		boolean f = false;
		try {
			String sql = "insert into todo_app(name,todo,status) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, todo);
			preparedStatement.setString(3, status);
			
			int i = preparedStatement.executeUpdate();
			if (i==1) {
				f=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public List<ToDo> gettoDos() {
		List<ToDo> list = new ArrayList<>();
		ToDo toDo = null;
		try {
			String sql = "select 8 from todo_app";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				toDo = new ToDo();
				toDo.setId(rs.getInt(1));
				toDo.setName(rs.getString(2));
				toDo.setTodo(rs.getString(3));
				toDo.setStatus(rs.getString(4));
				list.add(toDo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ToDo getTodoById( int id)
	{
		List<ToDo> list = new ArrayList<>();
		ToDo t = null;
		try {
			String sql = "select * grom todo_app where Id=?";
			PreparedStatement preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				t = new ToDo();
				t.setId(rs.getInt(1));
				t.setName(rs.getString(2));
				t.setTodo(rs.getString(3));
				t.setStatus(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public boolean updateTodo(ToDo t) {
		boolean f = false;
		
		try {
			String sql = "update todo_app set name=?,todo=?,status=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setString(2, t.getTodo());
			preparedStatement.setString(3, t.getStatus());
			preparedStatement.setInt(4, t.getId());
			
			int i = preparedStatement.executeUpdate();
			if (i==1) {
				f=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public boolean deleteId(int id) {
		boolean f = false;
		try {
			String sql = "delete from todo_app where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int i = preparedStatement.executeUpdate();
			if (i==1)
			{
				f=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
		
	}
	
}
