<%@page import="java.util.List"%>
<%@page import="com.ToDo.ToDo"%>
<%@page import="com.ToDo.ToDoDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.persistence.Persistence"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="component/all_css.jsp" %>
</head>
<body>
	
	<%@include file="component/navBar.jsp" %>
	
<%-- 	<%
		Connection connection = ToDoDao.gettconn();
		out.print(connection);
	%> --%>
	
	<h1 class="text-center text-success">ToDO List</h1>
	
	<div class="container">
		<table class="table table-striped" border="2px">
		  <thead class = "bg-success text-white">
		  	<tr>
		  		<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col">ToDo</th>
				<th scope="col">Status</th>
				<th scope="col">Action</th>
		  	</tr>
		  </thead>
		  <tbody>
		  
		  <%
		  		ToDoDao todo = new ToDoDao(ToDoDao.gettconn());
		  		List<ToDo> todo2 = todo.gettoDos();
		  		for(ToDo do1 : todo2)
		  		{%>
				<tr>
		  		<td scope="row"><%=do1.getId()%></td>
		  		<td scope="col"><%=do1.getName()%></td>
		  		<td><%=do1.getTodo()%></td>
		  		<td><%=do1.getStatus()%></td>
		  		<td>
		  			<a href="edit.jsp?id=<%=do1.getId() %>" class="btn  btn-sm btn-success">Edit</a>
		  			<a href="delete.jsp?id=<%=do1.getId()%>" class="btn btn- sm btn-danger">Delete</a>
		  		</td>
		  	</tr>		  				
		  		<% }
		  		%>
		  </tbody>
		</table>
	</div>
		
</body>
</html>