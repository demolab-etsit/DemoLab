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

<p>${votaciones[0][0].NVotos}</p>
	
	<table>
		
		  <tr>
		
		    <th scope="col">Escaños</th>
		    
			<c:forEach items="${partido_list}" var="part">
			
			    <th>${part.acronimo}</th>
		    
			</c:forEach>
		  </tr>
		
		
		<c:forEach items="${circunscripcion_list}" var="circ">
		
		  <tr>
		
		    <th>${circ.nombre}</th>
		    <c:forEach items="${partido_list}" var="part">
			
			    <td>${votaciones[i][j].NVotos}</td>
			    
		    
			</c:forEach>
		
		
		  </tr>
		</c:forEach>
		 
		
		</table>
		

	
	
</body>
</html>