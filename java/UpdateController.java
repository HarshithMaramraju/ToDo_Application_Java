package com.ToDo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateController extends HttpServlet {
       
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int id = Integer.parseInt(req.getParameter("id"));
    	String name = req.getParameter("name");
		String task = req.getParameter("todo");
		String status = req.getParameter("status");
		
		ToDoDao toDoDao = new ToDoDao(ToDoDao.gettconn());
		ToDo t = new ToDo();
		t.setId(id);
		t.setName(name);
		t.setTodo(task);
		t.setStatus(status);
		boolean f = toDoDao.updateTodo(t);
		HttpSession httpSession = req.getSession();
		if (f) {
			httpSession.setAttribute("sucMsg", "ToDo update Successfully");
			resp.sendRedirect("index.jsp");
		}
		else
		{
			httpSession.setAttribute("failedMessage", "ToDo failed to update");
			resp.sendRedirect("index.jsp");
		}
		
	}

}
