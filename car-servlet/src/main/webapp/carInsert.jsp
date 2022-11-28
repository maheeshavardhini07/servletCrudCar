<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Car</title>
<style type="text/css">
<
style>table, th, td {
	border: 5px black;
}

table {
	width: 40%;
}
td {
   width: 50% 
}
</style>
</head>

<body>
	<div align="center">
		<h1>Shiv Car Showroom</h1>
		<h2>Add New Car Details Here</h2>
	</div>
	<div align="center">
		<form action="<%=request.getContextPath()%>/insertCar" method="post">
			<table border="2">
				<tr>
					<th>Brand</th>
					<td><input style="width:330px; type="text" name="brand" placeholder="Brand"/></td>
				</tr>
				<tr>
					<th>Model</th>
					<td><input style="width:330px; type="text" name="model" placeholder="Model" /></td>
				</tr>
				<tr>
					<th>Price</th>
					<td><input style="width:330px; type="number" name="price" placeholder="Price" /></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><button id="buttonDemo"
							style="background: green;">Save</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
