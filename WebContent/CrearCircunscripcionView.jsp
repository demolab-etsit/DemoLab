<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Crear Circunscripción</title>
		<style>
		     <%@ include file="indexView.css"%>
		</style>
	</head>
	<body>
		<h1>Crear Circunscripcion</h1>
		<form action="CrearCircunscripcionServlet" method="post">
			<p>
				Nombre: <input type="text" name="name" />
			</p>
			<p>
				Número de electores: <input type="number" name="NElec" />
			</p>
			<p>
				Número de escaños que aporta al Parlamento: <input type="number" name="NEscanos" />
			</p>
			<p>
				<button  class="button" type="submit">Crear Circunscripcion</button>
				<a class="button" href="IndexView.jsp">Volver</a>
			</p>
		</form>
		<h3>Listado de Circunscripciones</h3>
		<table border="1">
			<tr>
				<th>Nombre</th>
				<th>Número de electores</th>
				<th>Número máximo de escaños</th>
			</tr>
			<c:forEach items="${circunscripcion_list}" var="circ">
				<tr>
					<td>${circ.nombre }</td>
					<td>${circ.NElectores }</td>
					<td>${circ.NMaxEscanos }</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
