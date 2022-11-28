<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Servlet</title>
<style type="text/css">
<
style>table, th, td {
	border: 5px black;
}

table {
	width: 40%;
}
td {
   width: 60% 
}
</style>
</head>

<body>
	<div align="center">
		<h1>Shiv Car Showroom</h1>
		<h2>Update Car Details Here</h2>
	</div>
	<div align="center">
		<form action="<%=request.getContextPath()%>/updateCar" method="post">
			<table border="2">
				<tr>
					<th>Car Id</th>
					<td><input style="width:330px; type="number" name="id" placeholder="Car Id"/></td>
				</tr>
				<tr>
					<th>Brand</th>
					<td><input style="width:330px; type="text" name="brand" placeholder="Brand"/></td>
				</tr>
				<tr>
					<th>Model</th>
					<td><input style="width:330px; type="text" name="model" placeholder="Model"/></td>
				</tr>
				<tr>
					<th>Price</th>
					<td><input style="width:330px; type="number" name="price" placeholder="Price"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button id="buttonDemo"
							style="background: green;">Save</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>