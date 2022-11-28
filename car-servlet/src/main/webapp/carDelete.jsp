<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Servlet</title>
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
	<div>
		<form action="<%=request.getContextPath()%>/deleteCar" method="post">
			<table align="center" border="2">
				<tr>
					<td align="center">Car Id</td>
					<td><input style="width: 330px;" text" name="id"
						placeholder="Enter the Car Id" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button id="delete"
							style="background: red;">Delete</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>