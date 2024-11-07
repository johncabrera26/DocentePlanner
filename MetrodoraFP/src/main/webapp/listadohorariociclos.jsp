<%-- 
    Document   : listadohorariociclos
    Created on : 09-abr-2024, 13:35:05
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
<h1>HorarioCiclos</h1>
<ul>
    <c:forEach items="${horariociclo}" var="horariociclo">
        <li>${horariociclo.diaSemana} ${horariociclo.horaInicio}</li>
    </c:forEach>
</ul>
</body>
</html>
