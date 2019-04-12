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
<%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/indexView.css" /> --%>
<style>
     <%@ include file="indexView.css"%>
</style>
</head>
<body>

	<!-- Mensaje de bienvenida -->
	<h1>Bienvenido a DemoLab</h1>
	
	<!-- Creación de partidos circunscripciones y en un futuro elecciones -->
	
	
	<!-- <a href="CrearEleccionView.jsp">Crear eleccion</a> -->
	
	<!-- Formulario con un botón que nos lleva a la vista de la tabla de votos -->
	<form method="GET" action="TablaServlet">
	<a class="button" href="CrearPartidoView.jsp">Crear partido</a>
	<a class="button" href="CrearCircunscripcionView.jsp">Crear circunscripcion</a>
			<!-- Menú desplegable para elegir las elecciones -->
			
			<%-- <select name="listaElecciones">
				<option value="" disabled selected>Elija elecciones</option>
				<c:forEach items="${eleccion_list}" var="eleccionesi">
					<option name=eleccionPOST value="${ eleccionesi.fecha}">
						${eleccionesi.fecha}-${eleccionesi.tipo}</option>
				</c:forEach>
			</select> --%>
	
	
	<button class="button" type="submit">Simular</button>
	
	
	</form>
	

	
	
</body>
</html>