package com.ToDo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt((req.getParameter("id")));
		
		ToDoDao dao = new ToDoDao(ToDoDao.gettconn());
		boolean f = dao.deleteId(id);
		HttpSession httpSession = req.getSession();
		if (f) {
			httpSession.setAttribute("sucMsg", "ToDo deleted Successfully");
			resp.sendRedirect("index.jsp");
		}
		else
		{
			httpSession.setAttribute("failedMessage", "ToDo failed to deletef");
			resp.sendRedirect("index.jsp");
		}
		
		
	}

}
