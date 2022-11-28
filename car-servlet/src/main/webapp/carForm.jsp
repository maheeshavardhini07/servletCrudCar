<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Form</title>
<style type="text/css">
<
style>table, th, td {
	border: 5px black;
}
</style>
</head>
<body style="color: rgb(134, 6, 66);">
	<div align="center">
		<h1>Shiv Car Showroom</h1>
		<h2>
			<a href="/insert">Add Car Details</a> &nbsp;&nbsp;&nbsp; 
			<a href="/list">Car
				Details</a>
		</h2>
	</div>
	<div align="center">
		<c:if test="${car != null}">
			<form action="update" method="post"></form>
		</c:if>

		<c:if test="${car == null}">
			<form action="insert" method="post"></form>
		</c:if>
		<form>
			<table border="1">
				<caption>
					<h2>
						<c:if test="${car != null}">
                        Update 
                    </c:if>

						<c:if test="${car == null}">
                        Add 
                    </c:if>
					</h2>
				</caption>
				<c:if test="${car != null}">
					<input type="hidden" name="id" <c:out value="${car.id}" /> />
				</c:if>
				<tr>
					<th>Brand:</th>
					<td><input type="text" name="brand" size="45"
						<c:out value="${car.brand}" /> /></td>
				</tr>
				<tr>
					<th>Model:</th>
					<td><input type="text" name="model" size="45"
						<c:out value="${car.model}" /> /></td>
				</tr>
				<tr>
					<th>Price:</th>
					<td><input type="text" name="price" size="5"
						<c:out value="${car.price}" /> /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>