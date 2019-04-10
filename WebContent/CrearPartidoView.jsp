<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Crear Partido</title>
	</head>
	<body>
		<h2>Crear Partido</h2>
		<form action="CrearPartidoServlet" method="post">
			<p>
				Nombre: <input type="text" name="name" />
			</p>
			<p>
				Color: <input type="text" name="color" />
			</p>
			<p>
				Acronimo: <input type="text" name="acronimo" />
			</p>
			<p>
				<button type="submit">Crear Partido</button>
			</p>
		</form>
		<h3>Listado de Partidos</h3>
		<table border="1">
			<tr>
				<th>Nombre</th>
				<th>Color</th>
				<th>Acronimo</th>
			</tr>
			<c:forEach items="${partido_list}" var="part">
				<tr>
					<td>${part.nombre }</td>
					<td>${part.color }</td>
					<td>${part.acronimo }</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
