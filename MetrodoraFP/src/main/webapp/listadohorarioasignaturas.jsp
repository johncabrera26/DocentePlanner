<%-- 
    Document   : listadohorarioasignaturas
    Created on : 09-abr-2024, 13:32:54
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
<h1> HorarioAsignatura</h1>
<ul>
    <c:forEach items="${horarioasignatura}" var="horarioasignatura">
        <li>${horarioasignatura.diaSemana} ${horarioasignatura.horaInicio}</li>
    </c:forEach>
</ul>
</body>
</html>
