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
<style>
     <%@ include file="css/indexView.css"%>
</style>
</head>
<body>
	<h1>Simulación</h1>
	<h3>Inserte el número de votos en la tabla</h3>
	<form method="POST" action="DHondtServlet">
		<table>

			<tr>
				<th scope="col">Votos</th>
				<c:forEach items="${partido_list}" var="part">
					<th>${part.acronimo}</th>
				</c:forEach>
			</tr>

			<c:forEach items="${circunscripcion_list}" var="circ">
				<tr>
					<th>${circ.nombre}</th>
					<c:forEach items="${partido_list}" var="part">
						<c:forEach items="${votaciones }" var="vota">
								<c:if
									test="${ vota.circunscripcion.nombre == circ.nombre && vota.partido.acronimo == part.acronimo }">
									<td>
									<input type="number" name="${part.acronimo}${circ.nombre}" min="0"
										max="${vota.circunscripcion.NElectores }"
										value="${vota.nvotosString }" />
									</td>
								</c:if>
						</c:forEach>
					</c:forEach>
				</tr>
			</c:forEach>


		</table>

		<a class="button" href="IndexView.jsp">Volver</a>

		<button class="button" type="submit">Simular</button>
	</form>
</body>
</html>