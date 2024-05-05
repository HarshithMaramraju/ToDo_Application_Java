package com.ToDo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;


@WebServlet("/add_todo")
public class ToDoController extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String task = req.getParameter("todo");
		String status = req.getParameter("status");
		
		System.out.println(name+" "+task+" "+status);
		
//		ToDo toDo = new ToDo();
//		toDo.setName(name);
//		toDo.setTodo(task);
//		toDo.setStatus(status);
		
		ToDoDao toDoDao = new ToDoDao(ToDoDao.gettconn());
		boolean f =  toDoDao.addToDo(name, task, status);
		
		HttpSession httpSession = req.getSession();
		
		if (f) {
			httpSession.setAttribute("sucMsg", "ToDo Added Successfully");
			resp.sendRedirect("index.jsp");
		}
		else
		{
			httpSession.setAttribute("failedMessage", "ToDo Added Successfully");
			resp.sendRedirect("index.jsp");
		}
		
		
	}

	
}
