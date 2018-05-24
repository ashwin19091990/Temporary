<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Retrieve Input Data</title>
</head>
<body>
	<%
		out.println("Hello Team"+"<br>");
	
	out.println("Please fill the necessary details below");
	
	
	%>
	<br />
	<br />
	<form action="welcome" method="post">

		<%
	
	ArrayList namelist = new ArrayList();
	
	BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
	String value;
	while((value = br.readLine()) != null){
	
	
	namelist.add(value);
	pageContext.setAttribute("namelist", namelist);
	
	}

	%>
		<%-- JSTL foreach tag example to loop an array in jsp --%>
		<c:forEach var="input" items="${namelist}">
			<c:out value="${input}" />
			<input type="text" name="${input}">
			<br />
		</c:forEach>
		<input type="submit" value="sumbit">

		<!-- <input type="submit" value="submit"> <br /> -->

	</form>

	<%
	
	ArrayList<String> list = (ArrayList<String>) request.getAttribute("list");
	
	if(list != null){
		out.println("Please find your entered details below"+"<br>");
	for (String currentvalue: list){
		
		out.println(currentvalue+"<br>");
		
		
	}
	}else{
		out.println("Your Id is not in the list"+"<br>");
		
	}
	
	%>

	<%
	
	ArrayList<String> employeeidlist = (ArrayList<String>) request.getAttribute("employeeidlist");
	
	if(employeeidlist != null){
		out.println("Please find the yet to be filled list"+"<br>");
	for (String currentvalue: employeeidlist){
		
		out.println(currentvalue+" ");
		
		
	}
	}
	
	%>


</body>
</html>