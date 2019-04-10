<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bienvenida</title>
</head>
<body>

	<!-- Mensaje de bienvenida -->
	<h1>Bienvenido a DemoLab</h1>
	
	<!-- Creación de partidos circunscripciones y en un futuro elecciones -->
	
	<a href="CrearPartidoView.jsp">Crear partido</a>
	<a href="CrearCircunscripcionView.jsp">Crear circunscripcion</a>
	<!-- <a href="CrearEleccionView.jsp">Crear eleccion</a> -->
	
	<!-- Formulario con un botón que nos lleva a la vista de la tabla de votos -->
	<form method="GET" action="TablaServlet">
	
			<!-- Menú desplegable para elegir las elecciones -->
			
			<%-- <select name="listaElecciones">
				<option value="" disabled selected>Elija elecciones</option>
				<c:forEach items="${eleccion_list}" var="eleccionesi">
					<option name=eleccionPOST value="${ eleccionesi.fecha}">
						${eleccionesi.fecha}-${eleccionesi.tipo}</option>
				</c:forEach>
			</select> --%>
	
	
	<button type="submit">Simular</button>
	
	
	</form>
	

	
	
</body>
</html>