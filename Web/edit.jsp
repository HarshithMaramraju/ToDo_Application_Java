<%@page import="com.ToDo.ToDo"%>
<%@page import="com.ToDo.ToDoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="component/all_css.jsp"%>
</head>
<body style="background-color: ; ">
	<%@include file="component/navBar.jsp"%>

	<%
		String sucMsg =(String)session.getAttribute("sucMsg");
		if(sucMsg!=null){%>
			<div class="alert alert-success" role="alert"><%=sucMsg%></div>
		<% 
			session.removeAttribute("sucMsg");
		}
	%>
	
	<%
		String failedMsg =(String)session.getAttribute("sucMsg");
		if(failedMsg!=null){%>
			<div class="alert alert-danger" role="alert"><%=sucMsg%></div>
		<% 
			session.removeAttribute("failedMsg");
		}
		%>	
		

	<div class="container" style="margin-top: auto; margin-bottom: auto;">
		<div class="row" p-5>
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-body">
					
					<h3 class="text-center text-success">Add ToDo</h3>
					<%
						int id = Integer.parseInt(request.getParameter("id"));
						ToDoDao dao = new ToDoDao(ToDoDao.gettconn());
						ToDo t =  dao.getTodoById(id);
					%>
						<form action="update" method="post">
						<input type="hidden" value="<%=t.getId()%>" name="id">
						<h3></h3>
							<div class="form-group">
								<label for="exampleInputEmail1">Name</label>
								<input type="text" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" name="name" value="<%= t.getName()%>">
							</div>
						
							<div class="form-group">
								<label for="exampleInputEmail1">ToDo</label>
								<input type="text" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" name="todo" value="<%=t.getTodo()%>">
							</div>

							<div class="form-group">
								<label for="inputState">Status</label>
								<select id="inputState" class="form-control" name="status">
								<%
									if("pending".equals(t.getStatus()))
									{%>
										<option value="Pending">Pending</option>
										<option value	="Completed">Completed</option>
									<% }else{%>
										<option value="Pending">Pending</option>
										<option value	="Completed">Completed</option>
									<% }%>
								</select>
							</div>
	
							<div class="text-center">
							<input type="submit" class="btn btn-primary" value="Update">							
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>