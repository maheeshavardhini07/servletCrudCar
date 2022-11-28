<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Of Car</title>
<style type="text/css">
<
style>table, th, td {
	border: 5px black;
}

table {
	width: 50%;
}
</style>
</head>
<body>
	<div align="center">
		<h1>Shiv Car Showroom</h1>
		<h2>
			List Of All Cars with Details
		</h2>
	</div>
	<div align="center">
		<%@ page import="java.sql.ResultSet"%>
		<%@ page import="java.sql.Statement"%>
		<%@ page import="java.sql.Connection"%>
		<%@ page import="java.sql.DriverManager"%>
		<form method="post">
			<table border="2">
				<tr>
					<td align="center"><b>Car ID </b></td>
					<td align="center"><b>Brand </b></td>
					<td align="center"><b>Model </b></td>
					<td align="center"><b>Price </b></td>
					<td align="center"><b>Delete </b></td>
					<td align="center"><b>Update </b></td>
				</tr>
				<%
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					String query = "select * from car";
					Connection conn = DriverManager.getConnection(
					"jdbc:ucanaccess://C:\\Users\\7000033011\\Maheesha\\java\\ms access\\Car.accdb;openExclusive=false;ignoreCase=true",
					"", "");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {
				%>
				<tr>
					<td align="center">
						<%
						out.println(rs.getInt("id"));
						%>
					</td>
					<td align="center">
						<%
						out.println(rs.getString("brand"));
						%>
					</td>
					<td align="center">
						<%
						out.println(rs.getString("model"));
						%>
					</td>
					<td align="center">
						<%
						out.println(rs.getInt("price"));
						%>
					</td>
					<td align="center"><a href="carDelete.jsp">Delete</a></td>
					<td align="center"><a href="carUpdate.jsp">Update</a></td>
				</tr>
				<%
				}
				%>
			</table>
			<%
			rs.close();
			stmt.close();
			conn.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
			%>
		</form>
	</div>
</body>
</html>