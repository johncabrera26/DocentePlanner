<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<h1>Profesor</h1>
<ul>
    <c:forEach items="${profesor}" var="profesor">
        <li>${profesor.nombre} ${profesor.apellido}</li>
    </c:forEach>
</ul>
</body>
</html>
 