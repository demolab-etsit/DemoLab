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
			
			    <td><input type="number" name="${part.acronimo}${circ.nombre}" min=0 ></td>
			    
		    
			</c:forEach>
		
		
		  </tr>
		</c:forEach>
		 
		
		</table>
		
		
		
		<button type="submit">Simular</button>
		</form>
	 
	
	<h3>
	<c:forEach items="${circunscripcion_list}" var="circ">
		
		 
		    <c:forEach items="${partido_list}" var="part">
			
			    ${part.acronimo}${circ.nombre}
			    
		    
			</c:forEach>
			
		</c:forEach>
	</h3>
	
	
</body>
</html>