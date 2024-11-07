<%-- 
    Document   : listadoasignaturas
    Created on : 09-abr-2024, 11:30:32
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<h1> Asignatura</h1>
<ul>
    <c:forEach items="${asignatura}" var="asignatura">
        <li>${asignatura.nombre} ${asignatura.horasSemanales}</li>
    </c:forEach>
</ul>
</body>
</html>
