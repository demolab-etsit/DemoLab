<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Crear Elecciones</title>
	</head>
	<body>
		<h2>Crear Elecciones</h2>
		<form action="CrearEleccionServlet" method="post">
			<p>
				Fecha: <input type="date" name="date" />
			</p>
			<p>
				Tipo: <input type="text" name="type" />
			</p>
			<p>
				<button type="submit">Crear Elecciones</button>
			</p>
		</form>
		<h3>Listado de Elecciones</h3>
		<table border="1">
			<tr>
				<th>Fecha</th>
				<th>Tipo</th>
				<th>Id</th>
			</tr>
			<c:forEach items="${eleccion_list}" var="eleccion">
				<tr>
					<td>${eleccion.fecha }</td>
					<td>${eleccion.tipo }</td>
					<td>${eleccion.id }</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
